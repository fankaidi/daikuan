package com.kensure.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import co.kensure.frame.Const;
import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.frame.ResultType;
import co.kensure.http.RequestUtils;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.DateUtils;
import co.kensure.mem.MapUtils;
import co.kensure.mem.NumberUtils;
import co.kensure.mem.Utils;
import co.kensure.sms.SMSClient;

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.user.model.ChannelInfo;
import com.kensure.ktl.user.model.LoanMoney;
import com.kensure.ktl.user.model.SmsInfo;
import com.kensure.ktl.user.model.UserInfo;
import com.kensure.ktl.user.model.UserLogin;
import com.kensure.ktl.user.service.ChannelInfoService;
import com.kensure.ktl.user.service.LoanMoneyService;
import com.kensure.ktl.user.service.SmsInfoService;
import com.kensure.ktl.user.service.TestService;
import com.kensure.ktl.user.service.UserInfoService;
import com.kensure.ktl.user.service.UserLoginService;

@Controller
@RequestMapping(value = "mobile")
@SuppressWarnings("rawtypes")
public class MobileController {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserLoginService userLoginService;

	@Resource
	private LoanMoneyService loanMoneyService;

	@Resource
	private SmsInfoService smsInfoService;

	@Resource
	private ChannelInfoService channelInfoService;

	@RequestMapping("home.do")
	public String home(HttpServletRequest req, HttpServletResponse rep, Model model) {
		try {
			JSONObject json = RequestUtils.paramToJson(req);
			Integer cid = json.getInteger("cid");
			ChannelInfo ci = new ChannelInfo();
			ci.setAgentno(RequestUtils.getAgent(req));
			ci.setCid(cid);
			ci.setCip(RequestUtils.getClientIp(req));
			ci.setDip(RequestUtils.getDip(req));
			ci.setRefurl(RequestUtils.getReferer(req));
			channelInfoService.insert(ci);
			model.addAttribute("uuid", ci.getId());
			return "mobile/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "page/error.jsp";
		}
	}

	/**
	 * qudao
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addchannel.do", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo addchannel(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		Integer cid = json.getInteger("cid");
		ChannelInfo ci = new ChannelInfo();
		ci.setAgentno(RequestUtils.getAgent(req));
		ci.setCid(cid);
		ci.setCip(RequestUtils.getClientIp(req));
		ci.setDip(RequestUtils.getDip(req));
		ci.setRefurl(RequestUtils.getReferer(req));
		channelInfoService.insert(ci);
		return new ResultRowInfo(ci.getId() + "");
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
			smsinfo.setQrcode(Utils.randomSMSCode());
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

		UserInfo usertemp = JSONObject.parseObject(json.toJSONString(), UserInfo.class);

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
			usertemp.setPwd(smscode);
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
		userLogin.setIp(req.getRemoteHost());
		userLoginService.insert(userLogin);

		// uuid
		Long uuid = json.getLong("uuid");
		channelInfoService.updateSuccess(uuid, usertemp.getMobile());

		return new ResultRowInfo(userLogin.getSessionid());
	}

	/**
	 * 用户资料
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@RequestMapping("userinfo.do")
	public String help(HttpServletRequest req, HttpServletResponse rep, Model model) {
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
	@RequestMapping(value = "updateuser.do", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo updateuser(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		UserLogin userse = userLoginService.selectBySessionId(sessionid);

		UserInfo user = userInfoService.selectOne(userse.getUserid());

		UserInfo usertemp = JSONObject.parseObject(json.toJSONString(), UserInfo.class);

		Date date = new Date();
		usertemp.setId(user.getId());
		usertemp.setPwd(user.getPwd());
		usertemp.setPhone(user.getPhone());
		usertemp.setCreateDate(user.getCreateDate());
		usertemp.setUpdateDate(date);
		userInfoService.update(usertemp);

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
			List<LoanMoney> loanlist = loanMoneyService.selectByWhere(MapUtils.genMap("statusisnull", "statusisnull", "orderby", " id desc "));
			if (CollectionUtils.isEmpty(loanlist)) {
				loanlist = new ArrayList<LoanMoney>();
			}
			for (LoanMoney lm : loanlist) {
				lm.setUserinfo(userInfoService.selectOne(lm.getUserid()));
			}
			model.addAttribute("list", loanlist);

			if (RequestUtils.isMobileAgent(req)) {
				return "mobile/adminmobilelist.jsp";
			} else {
				return "mobile/adminmoneylist.jsp";
			}
		} catch (Exception e) {
			return "page/error.jsp";
		}
	}

	@RequestMapping("adminread.do")
	public String adminread(HttpServletRequest req, HttpServletResponse rep, Model model) {
		try {
			JSONObject json = RequestUtils.paramToJson(req);
			String fromdate = json.getString("fromdate");
			String todate = json.getString("todate");
			Date date = new Date();
			String day = DateUtils.format(date, DateUtils.DAY_FORMAT);
			if (StringUtils.isBlank(fromdate)) {
				fromdate = day;
			}
			if (StringUtils.isBlank(todate)) {
				todate = day;
			}

			Date datefrom = DateUtils.parse(fromdate + " 00:00:00", DateUtils.DATE_FORMAT_PATTERN);
			Date dateto = DateUtils.parse(todate + " 23:59:59", DateUtils.DATE_FORMAT_PATTERN);

			List<LoanMoney> loanlist = loanMoneyService.selectByWhere(MapUtils.genMap("status", 1, "fromdate", datefrom, "todate", dateto, "orderby", " id desc "));
			if (CollectionUtils.isEmpty(loanlist)) {
				loanlist = new ArrayList<LoanMoney>();
			}
			for (LoanMoney lm : loanlist) {
				lm.setUserinfo(userInfoService.selectOne(lm.getUserid()));
			}
			model.addAttribute("fromdate", fromdate);
			model.addAttribute("todate", todate);
			model.addAttribute("list", loanlist);

			if (RequestUtils.isMobileAgent(req)) {
				return "mobile/adminmobileread.jsp";
			} else {
				return "mobile/adminmoneyread.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "page/error.jsp";
		}
	}

	@RequestMapping("userdo.do")
	public String userdo(HttpServletRequest req, HttpServletResponse rep, Model model) {
		try {
			JSONObject json = RequestUtils.paramToJson(req);
			long id = json.getLong("id");

			LoanMoney loan = loanMoneyService.selectOne(id);
			loan.setStatus(1);
			loanMoneyService.update(loan);

			UserInfo userinfo = userInfoService.selectOne(loan.getUserid());
			model.addAttribute("userinfo", userinfo);
			return "mobile/adminuserinfo.jsp";

		} catch (Exception e) {
			return "page/error.jsp";
		}
	}

	@ResponseBody
	@RequestMapping(value = "test.do", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo test(HttpServletRequest req, HttpServletResponse rep) {
		long peedc;
		try {
			peedc = TestService.insertPeople();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new ResultRowInfo(peedc + "");
	}

	@ResponseBody
	@RequestMapping(value = "testthread.do", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo testthread(HttpServletRequest req, HttpServletResponse rep) {
		long peedc;
		try {
			peedc = TestService.insertThreadPeople();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ResultRowInfo( peedc + "");
	}

	@ResponseBody
	@RequestMapping(value = "table.do", method = { RequestMethod.GET, RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo table(HttpServletRequest req, HttpServletResponse rep) {
		boolean peedc = false;
		try {
			peedc = TestService.tableexists();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new ResultRowInfo(peedc + "");
	}

	/**
	 * 查看渠道
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@RequestMapping("channel.do")
	public String channel(HttpServletRequest req, HttpServletResponse rep, Model model) {
		try {
			Date date = new Date();
			String day = DateUtils.format(date, DateUtils.DAY_FORMAT);
			String fromdate = day;
			String todate = day;
			model.addAttribute("fromdate", fromdate);
			model.addAttribute("todate", todate);
			return "mobile/recordlist.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "page/error.jsp";
		}
	}

	/**
	 * 查看渠道
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "channellist.do", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo channellist(HttpServletRequest req, HttpServletResponse rep) {

		JSONObject json = RequestUtils.paramToJson(req);
		String fromdate = json.getString("fromdate");
		String todate = json.getString("todate");
		Date date = new Date();
		String day = DateUtils.format(date, DateUtils.DAY_FORMAT);
		if (StringUtils.isBlank(fromdate)) {
			fromdate = day;
		}
		if (StringUtils.isBlank(todate)) {
			todate = day;
		}

		Date datefrom = DateUtils.parse(fromdate + " 00:00:00", DateUtils.DATE_FORMAT_PATTERN);
		Date dateto = DateUtils.parse(todate + " 23:59:59", DateUtils.DATE_FORMAT_PATTERN);
		Map<String, Object> parameters = MapUtils.genMap("fromdate", datefrom, "todate", dateto);

		// 所有渠道
		List<Map> channellist = channelInfoService.groupByCid(parameters);
		parameters.put("status", 1);

		// 成功渠道
		List<Map> succhannellist = channelInfoService.groupByCid(parameters);
		Map sucmap = new HashMap();
		for (Map s : succhannellist) {
			sucmap.put(s.get("cid"), s);
		}

		if (CollectionUtils.isEmpty(channellist)) {
			channellist = new ArrayList<Map>();
		}
		for (Map lm : channellist) {
			lm.put("succnt", 0);

			Map smap = (Map) sucmap.get(lm.get("cid"));
			if (smap != null) {
				lm.put("succnt", smap.get("cnt"));
			}

		}
		return new ResultRowsInfo(channellist);

	}
}
