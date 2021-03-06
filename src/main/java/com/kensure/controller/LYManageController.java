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
@RequestMapping(value = "lymanage")
public class LYManageController {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserLoginService userLoginService;

	@Resource
	private LoanMoneyService loanMoneyService;

	@Resource
	private SmsInfoService smsInfoService;

	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping("page1.do")
	public String page1(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "ly/manage/recordlist.jsp";
	}

	/**
	 * 
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
