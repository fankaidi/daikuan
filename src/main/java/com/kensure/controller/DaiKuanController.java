package com.kensure.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.http.RequestUtils;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.MapUtils;
import co.kensure.mem.NumberUtils;
import co.kensure.mem.Utils;
import co.kensure.sms.SMSClient;

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.user.model.LoanMoney;
import com.kensure.ktl.user.model.SmsInfo;
import com.kensure.ktl.user.model.UserInfo;
import com.kensure.ktl.user.model.UserLogin;
import com.kensure.ktl.user.service.LoanMoneyService;
import com.kensure.ktl.user.service.SmsInfoService;
import com.kensure.ktl.user.service.TestService;
import com.kensure.ktl.user.service.UserInfoService;
import com.kensure.ktl.user.service.UserLoginService;

/**
 * 贷款业务的页面跳转
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "daikuan")
public class DaiKuanController {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserLoginService userLoginService;

	@Resource
	private LoanMoneyService loanMoneyService;

	@Resource
	private SmsInfoService smsInfoService;

	@RequestMapping("home.do")
	public String home(HttpServletRequest req, HttpServletResponse rep,Model model) {
		try {
			return "mobile/index.jsp";
		} catch (Exception e) {
			return "page/error.jsp";
		}
	}

	/**
	 * 验证码发送
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "smsuser.do", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo smsuser(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String mobile = json.getString("mobile");
		// UserInfo user = userInfoService.selectByMobile(mobile);

		SmsInfo smsinfo = smsInfoService.selectByMobile(mobile);
		Date date = new Date();
		if (smsinfo == null) {
			smsinfo = new SmsInfo();
			smsinfo.setCreateDate(date);
			smsinfo.setMobile(mobile);
			smsinfo.setId(date.getTime());
			smsinfo.setQrcode(Utils.randomSMSCode());
			smsinfo.setUpdateDate(date);
			smsInfoService.insert(smsinfo);
		} else {
			smsinfo.setUpdateDate(date);
			smsInfoService.update(smsinfo);
		}

		try {
			SMSClient.sendSMSMessage(mobile, smsinfo.getQrcode(), 30);
		} catch (Exception e) {
			BusinessExceptionUtil.threwException("发送短信失败");
		}
		return new ResultRowInfo();
	}

	/**
	 * 用户注册数据保存,借款记录增加
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "commituser.do", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo adduser(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String smscode = json.getString("smscode");
		String money = json.getString("money");

		UserInfo usertemp = JSONObject.parseObject(json.toJSONString(),
				UserInfo.class);

		SmsInfo smsinfo = smsInfoService.selectByMobile(usertemp.getMobile());
		if (smsinfo == null || !smsinfo.getQrcode().equals(smscode)) {
			BusinessExceptionUtil.threwException("验证码错误！");
		}

		// 用户信息
		UserInfo user1 = userInfoService.selectByMobile(usertemp.getMobile());
		Date date = new Date();
		if (user1 == null) {
			usertemp.setId(date.getTime());
			usertemp.setCreateDate(date);
			usertemp.setUpdateDate(date);
			userInfoService.insert(usertemp);
			smsinfo.setUserid(usertemp.getId());
			smsInfoService.update(smsinfo);
			user1 = usertemp;
		}

		// 借贷信息
		LoanMoney m = new LoanMoney();
		m.setId(date.getTime());
		m.setCreateDate(date);
		m.setUpdateDate(date);
		m.setMoney(NumberUtils.parseInteger(money, 0));
		m.setUserid(user1.getId());
		loanMoneyService.insert(m);

		// 用户登录
		UserLogin userLogin = new UserLogin();
		userLogin.setId(date.getTime());
		userLogin.setCreateDate(date);
		userLogin.setUpdateDate(date);
		userLogin.setUserid(user1.getId());
		userLogin.setSessionid(req.getSession().getId());
		userLogin.setIp(req.getRemoteHost());
		userLoginService.insert(userLogin);

		return new ResultRowInfo(req.getSession().getId());
	}

	/**
	 * 用户资料
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@RequestMapping("userinfo.do")
	public String help(HttpServletRequest req, HttpServletResponse rep,
			Model model) {
		try {
			JSONObject json = RequestUtils.paramToJson(req);
			String sessionId = json.getString("id");
			UserLogin userse = userLoginService.selectBySessionId(sessionId);
			UserInfo user = userInfoService.selectOne(userse.getUserid());
			if (StringUtils.isNotBlank(user.getQq())) {
				rep.sendRedirect("/mobile/moneylist.do?id=" + sessionId);
			}
			model.addAttribute("id", sessionId);
			return "mobile/userinfo.jsp";
		} catch (Exception e) {
			return "page/error.jsp";
		}
	}
	
	
	/**
	 * 修改用户资料
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updateuser.do", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo updateuser(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		UserLogin userse = userLoginService.selectBySessionId(sessionid);
		
		UserInfo user = userInfoService.selectOne(userse.getUserid());
		
		UserInfo usertemp = JSONObject.parseObject(json.toJSONString(),UserInfo.class);
		
		Date date = new Date();
		usertemp.setUpdateDate(date);
		user.setName(usertemp.getName());
		user.setCard(usertemp.getCard());
		user.setQq(usertemp.getQq());
		user.setZhimafen(usertemp.getZhimafen());
		user.setHuabeiedu(usertemp.getHuabeiedu());
		user.setJiebeiedu(usertemp.getJiebeiedu());
		user.setJiedaibao(usertemp.getJiedaibao());
		user.setYear(usertemp.getYear());
		user.setXb(usertemp.getXb());	
		userInfoService.update(user);

		return new ResultRowInfo(sessionid);
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
	public String moneylist(HttpServletRequest req, HttpServletResponse rep,
			Model model) {
		try {
			JSONObject json = RequestUtils.paramToJson(req);
			String sessionId = json.getString("id");
			UserLogin userse = userLoginService.selectBySessionId(sessionId);
			List<LoanMoney> loanlist = loanMoneyService.selectByWhere(MapUtils.genMap("userid",userse.getUserid()));
			model.addAttribute("list", loanlist);
			return "mobile/moneylist.jsp";
		} catch (Exception e) {
			return "page/error.jsp";
		}
	}
	
	
	@RequestMapping("admin.do")
	public String adminmoneylist(HttpServletRequest req, HttpServletResponse rep,Model model) {
		try {
			List<LoanMoney> loanlist = loanMoneyService.selectByWhere(MapUtils.genMap("orderby"," id desc "));
			if(CollectionUtils.isEmpty(loanlist)){
				loanlist = new ArrayList<LoanMoney>();
			}
			for(LoanMoney lm : loanlist){
				lm.setUserinfo(userInfoService.selectOne(lm.getUserid()));		
			}
			model.addAttribute("list", loanlist);
			return "mobile/adminmoneylist.jsp";
		} catch (Exception e) {
			return "page/error.jsp";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "test.do", method ={ RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo test(HttpServletRequest req, HttpServletResponse rep) {
		long peedc;
		try {
			peedc = TestService.insertPeople();
		} catch (IOException e) {
		   throw new RuntimeException(e);
		}
		return new ResultRowInfo(peedc+"");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "testthread.do", method ={ RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo testthread(HttpServletRequest req, HttpServletResponse rep) {
		long peedc;
		try {
			peedc = TestService.insertThreadPeople();
		} catch (Exception e) {
		   throw new RuntimeException(e);
		}
		return new ResultRowInfo(peedc+"");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "table.do", method ={ RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo table(HttpServletRequest req, HttpServletResponse rep) {
		boolean peedc = false;
		try {
			peedc = TestService.tableexists();
		} catch (IOException e) {
		   throw new RuntimeException(e);
		}
		return new ResultRowInfo(peedc+"");
	}
	
}
