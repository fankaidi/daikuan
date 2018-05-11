package com.kensure.controller.user.data;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kensure.exception.ParamUtils;
import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.http.RequestUtils;

import com.alibaba.fastjson.JSONObject;
import com.kensure.mycom.config.model.MyConfig;
import com.kensure.mycom.config.service.MyConfigService;
import com.kensure.mycom.user.model.ChannelDefine;
import com.kensure.mycom.user.model.MyWeixin;
import com.kensure.mycom.user.model.UserSession;
import com.kensure.mycom.user.service.MyUserLoginService;
import com.kensure.mycom.user.service.UserWeixinService;

/**
 * 微信登录类
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "user/weixin")
public class WeiXinController {

	@Resource
	private UserWeixinService userWeixinService;

	@Resource
	private MyUserLoginService myUserLoginService;

	/**
	 * 试压小程序，微信用户登录
	 * */
	@ResponseBody
	@RequestMapping(value = "login.do", produces = "application/json;charset=UTF-8")
	public ResultInfo login(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String code = json.getString("code");
		Integer channelId = json.getInteger("channelId");
		ParamUtils.isBlankThrewException(code, "代码不能为空");
		ParamUtils.isBlankThrewException(channelId, "id不能为空");

		MyConfig appid = MyConfigService.getMyConfig(ChannelDefine.F_WEIXIN_APPID + channelId);
		MyConfig secret = MyConfigService.getMyConfig(ChannelDefine.F_WEIXIN_SECRET + channelId);
		ChannelDefine cd = new ChannelDefine(channelId, appid.getName(), secret.getName());
		UserSession userSession = userWeixinService.doLogin(code, cd);
		return new ResultRowInfo(userSession);
	}

	/**
	 * 微信用户的信息，如头像、名称
	 * */
	@ResponseBody
	@RequestMapping(value = "addUser.do", produces = "application/json;charset=UTF-8")
	public ResultInfo addUser(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		ParamUtils.checkSessionid(json);
		String sessionid = ParamUtils.getSessionid(json);
		MyWeixin myWeixin = JSONObject.parseObject(json.toJSONString(), MyWeixin.class);
		userWeixinService.addWeixin(sessionid, myWeixin);
		return new ResultRowInfo();
	}

	/**
	 * 微信用户的信息,如头像、名称
	 * */
	@ResponseBody
	@RequestMapping(value = "getUser.do", produces = "application/json;charset=UTF-8")
	public ResultInfo getUser(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);	
		ParamUtils.checkSessionid(json);
		String sessionid = ParamUtils.getSessionid(json);
		MyWeixin weixin = userWeixinService.getWeixin(sessionid);
		return new ResultRowInfo(weixin);
	}

	/**
	 * 获取用户会话信息，也就是openid
	 * */
	@ResponseBody
	@RequestMapping(value = "getOpen.do", produces = "application/json;charset=UTF-8")
	public ResultInfo getOpen(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		ParamUtils.checkSessionid(json);
		String sessionid = ParamUtils.getSessionid(json);
		UserSession userSession = myUserLoginService.getUserSessionBySessionId(sessionid);
		return new ResultRowInfo(userSession);
	}
	
	
	/**
	 * 提交电话号码，创建真正的用户
	 * */
	@ResponseBody
	@RequestMapping(value = "mobileUser.do", produces = "application/json;charset=UTF-8")
	public ResultInfo mobileUser(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);	
		ParamUtils.checkSessionid(json);
		String sessionid = ParamUtils.getSessionid(json);
		String mobile = json.getString("mobile");
		userWeixinService.mobileUser(sessionid,mobile);
		return new ResultRowInfo();
	}

}
