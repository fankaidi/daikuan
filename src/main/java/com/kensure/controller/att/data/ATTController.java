package com.kensure.controller.att.data;

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
import com.kensure.mycom.user.service.MyUserLoginService;
import com.kensure.mycom.user.service.UserWeixinService;

/**
 * 试压终端客户控制类
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "att/pipeclient")
public class ATTController {

	@Resource
	private UserWeixinService userWeixinService;

	@Resource
	private MyUserLoginService myUserLoginService;

	/**
	 * 试压下预约订单
	 * */
	@ResponseBody
	@RequestMapping(value = "pipeAdd.do", produces = "application/json;charset=UTF-8")
	public ResultInfo pipeAdd(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		ParamUtils.checkSessionid(json);
		String sessionid = ParamUtils.getSessionid(json);
		
		String code = json.getString("qita信息");

		return new ResultRowInfo();
	}

}
