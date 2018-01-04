package com.kensure.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.kensure.http.RequestUtils;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.MapUtils;

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.user.model.LoanMoney;
import com.kensure.ktl.user.model.UserInfo;
import com.kensure.ktl.user.model.UserLogin;
import com.kensure.ktl.user.service.LoanMoneyService;
import com.kensure.ktl.user.service.SmsInfoService;
import com.kensure.ktl.user.service.UserInfoService;
import com.kensure.ktl.user.service.UserLoginService;

/**
 * 旅游管理业务的页面跳转
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "show")
public class LYShowController {
	
	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserLoginService userLoginService;

	@Resource
	private LoanMoneyService loanMoneyService;

	@Resource
	private SmsInfoService smsInfoService;

	/**
	 * 首页
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@RequestMapping("index.do")
	public String page1(HttpServletRequest req, HttpServletResponse rep, Model model) {
		try {
			return "ly/show/index.jsp";
		} catch (Exception e) {
			return "page/error.jsp";
		}
	}

	/**
	 * 新增页面
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@RequestMapping("add.do")
	public String help(HttpServletRequest req, HttpServletResponse rep, Model model) {
		JSONObject json = RequestUtils.paramToJson(req);
		String id = json.getString("id");
		if (StringUtils.isBlank(id)) {
			id = "";
		}
		model.addAttribute("id", id);
		return "ly/manage/add.jsp";
	}

	/**
	 * 查看贷款记录
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@RequestMapping("moneylist.do")
	public String moneylist(HttpServletRequest req, HttpServletResponse rep, Model model) {
		try {
			JSONObject json = RequestUtils.paramToJson(req);
			String sessionId = json.getString("id");
			UserLogin userse = userLoginService.selectBySessionId(sessionId);
			List<LoanMoney> loanlist = loanMoneyService.selectByWhere(MapUtils.genMap("userid", userse.getUserid()));
			model.addAttribute("list", loanlist);
			return "mobile/moneylist.jsp";
		} catch (Exception e) {
			return "page/error.jsp";
		}
	}

	
	@RequestMapping("admin.do")
	public String adminmoneylist(HttpServletRequest req, HttpServletResponse rep, Model model) {
		try {
			List<LoanMoney> loanlist = loanMoneyService.selectByWhere(MapUtils.genMap("orderby", " id desc "));
			if (CollectionUtils.isEmpty(loanlist)) {
				loanlist = new ArrayList<LoanMoney>();
			}
			for (LoanMoney lm : loanlist) {
				lm.setUserinfo(userInfoService.selectOne(lm.getUserid()));
			}
			model.addAttribute("list", loanlist);
			return "mobile/adminmoneylist.jsp";
		} catch (Exception e) {
			return "page/error.jsp";
		}
	}
}
