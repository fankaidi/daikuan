package com.kensure.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.exception.ParamUtils;
import co.kensure.frame.Const;
import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.frame.ResultType;
import co.kensure.http.RequestUtils;
import co.kensure.mem.MapUtils;

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.liuliang.model.LLBaseInfo;
import com.kensure.ktl.liuliang.model.LLCaiGouInfo;
import com.kensure.ktl.liuliang.model.LLMealInfo;
import com.kensure.ktl.liuliang.model.LLMealSale;
import com.kensure.ktl.liuliang.model.LLNotice;
import com.kensure.ktl.liuliang.model.LLUserInfo;
import com.kensure.ktl.liuliang.model.LLUserLogin;
import com.kensure.ktl.liuliang.service.LLBaseInfoService;
import com.kensure.ktl.liuliang.service.LLCaiGouInfoService;
import com.kensure.ktl.liuliang.service.LLMealInfoService;
import com.kensure.ktl.liuliang.service.LLMealSaleService;
import com.kensure.ktl.liuliang.service.LLNoticeService;
import com.kensure.ktl.liuliang.service.LLUserInfoService;
import com.kensure.ktl.liuliang.service.LLUserLoginService;

/**
 * 流量业务的逻辑处理
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "liuliangpost")
public class LiuLiangPostController {

	@Resource
	private LLUserInfoService llUserInfoService;

	@Resource
	private LLUserLoginService llUserLoginService;

	@Resource
	private LLBaseInfoService llBaseInfoService;

	@Resource
	private LLCaiGouInfoService llCaiGouInfoService;

	@Resource
	private LLMealSaleService llMealSaleService;

	@Resource
	private LLMealInfoService llMealInfoService;

	@Resource
	private LLNoticeService llNoticeService;

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("userlogin.do")
	public ResultInfo userlogin(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String username = json.getString("username");
		String password = json.getString("password");
		LLUserLogin userLogin = llUserLoginService.login(username, password, req.getRemoteHost());
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS, userLogin);
	}

	/**
	 * 用户注销
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("userlogout.do")
	public ResultInfo userlogout(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionId = json.getString("sessionid");
		llUserLoginService.loginOut(sessionId);
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS);
	}

	/**
	 * yoghurt注册
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "adduser.do")
	public ResultInfo adduser(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String username = json.getString("username");
		String password = json.getString("password");
		String mobile = json.getString("mobile");
		ParamUtils.isBlankThrewException(username, "用户名不能为空");
		ParamUtils.isBlankThrewException(password, "密码不能为空");
		ParamUtils.isBlankThrewException(mobile, "预留电话不能为空");

		// 用户信息
		LLUserInfo user = llUserInfoService.selectByLoginName(username);
		if (user != null) {
			BusinessExceptionUtil.threwException("该用户名已有，请重新填写");
		}
		user = new LLUserInfo();
		user.setName(username);
		user.setLoginname(username);
		user.setNickname(username);
		user.setMobile(mobile);
		user.setPwd(password);
		llUserInfoService.insert(user);

		// 用户登录
		LLUserLogin userLogin = llUserLoginService.login(username, password, req.getRemoteHost());
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS, userLogin);
	}

	/**
	 * 根据会话id获取用户信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("userinfo.do")
	public ResultInfo userinfo(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionId = json.getString("sessionid");
		LLUserInfo user = llUserInfoService.selectBySessionId(sessionId);
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS, user);
	}

	/**
	 * 获取最新流量信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("newbaseinfo.do")
	public ResultInfo newbaseinfo(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (userinfo == null) {
			BusinessExceptionUtil.threwException("找不到该用户");
		}

		Map<String, Object> parameters = new HashMap<>();
		MapUtils.putStartTime(parameters, "startTime", json.getString("startTime"));
		MapUtils.putEndTime(parameters, "endTime", json.getString("endTime"));
		MapUtils.putString(parameters, "qudao", json.getString("qudao"));
		MapUtils.putString(parameters, "name", json.getString("name"));
		MapUtils.putString(parameters, "shebei", json.getString("shebei"));
		MapUtils.putInt(parameters, "minzhimafen", json.getString("minzhimafen"));
		MapUtils.putInt(parameters, "maxzhimafen", json.getString("maxzhimafen"));
		MapUtils.putInt(parameters, "minyear", json.getString("minyear"));
		MapUtils.putInt(parameters, "maxyear", json.getString("maxyear"));
		MapUtils.putPageInfo(parameters, json);
		parameters.put("userId", userinfo.getId());

		List<LLBaseInfo> list = llBaseInfoService.selectNewInfo(parameters);
		int size = (int) llBaseInfoService.selectNewInfoCount(parameters);
		return new ResultRowsInfo(list, size);
	}

	/**
	 * 获取已经购买的流量信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("goumaibaseinfo.do")
	public ResultInfo goumaibaseinfo(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (userinfo == null) {
			BusinessExceptionUtil.threwException("找不到该用户");
		}

		Map<String, Object> parameters = new HashMap<>();
		MapUtils.putStartTime(parameters, "startTime", json.getString("startTime"));
		MapUtils.putEndTime(parameters, "endTime", json.getString("endTime"));
		MapUtils.putString(parameters, "qudao", json.getString("qudao"));
		MapUtils.putString(parameters, "name", json.getString("name"));
		MapUtils.putString(parameters, "shebei", json.getString("shebei"));
		MapUtils.putInt(parameters, "minzhimafen", json.getString("minzhimafen"));
		MapUtils.putInt(parameters, "maxzhimafen", json.getString("maxzhimafen"));
		MapUtils.putInt(parameters, "minyear", json.getString("minyear"));
		MapUtils.putInt(parameters, "maxyear", json.getString("maxyear"));
		MapUtils.putPageInfo(parameters, json);
		parameters.put("userId", userinfo.getId());

		List<LLCaiGouInfo> list = llCaiGouInfoService.selectGouMai(parameters);
		int size = (int) llCaiGouInfoService.selectGouMaiCount(parameters);
		return new ResultRowsInfo(list, size);
	}

	/**
	 * 购买流量
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("goumailiuliang.do")
	public ResultInfo goumailiuliang(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionId = json.getString("sessionid");
		String id = json.getString("id");
		llCaiGouInfoService.goumaiLL(sessionId, id);
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS);
	}

	/**
	 * 订单查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("xiaofei.do")
	public ResultInfo xiaofei(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (userinfo == null) {
			BusinessExceptionUtil.threwException("找不到该用户");
		}

		Map<String, Object> parameters = new HashMap<>();
		MapUtils.putPageInfo(parameters, json);
		parameters.put("userId", userinfo.getId());
		parameters.put("orderby", "create_date desc");
		List<LLMealSale> list = llMealSaleService.selectByWhereByDict(parameters);
		long size = llMealSaleService.selectCountByWhere(parameters);
		return new ResultRowsInfo(list, size);
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updatepwd.do")
	public ResultInfo updatepwd(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (userinfo == null) {
			BusinessExceptionUtil.threwException("找不到该用户");
		}
		String oldpwd = json.getString("oldpwd");
		String newpwd = json.getString("newpwd");
		String copypwd = json.getString("copypwd");
		ParamUtils.isBlankThrewException(oldpwd, "原密码不能为空");
		ParamUtils.isBlankThrewException(newpwd, "新密码不能为空");
		ParamUtils.isBlankThrewException(copypwd, "确认密码不能为空");

		if (!oldpwd.equalsIgnoreCase(userinfo.getPwd())) {
			BusinessExceptionUtil.threwException("原密码输入有误");
		}
		if (!newpwd.equalsIgnoreCase(copypwd)) {
			BusinessExceptionUtil.threwException("确认密码和新密码不一致");
		}
		userinfo.setPwd(newpwd);
		llUserInfoService.update(userinfo);
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS);
	}

	/**
	 * 最新通知
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("newnotice.do")
	public ResultInfo newnotice(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (userinfo == null) {
			BusinessExceptionUtil.threwException("找不到该用户");
		}

		Map<String, Object> parameters = new HashMap<>();
		MapUtils.putPageInfo(parameters, json);
		parameters.put("userId", userinfo.getId());
		parameters.put("orderby", "create_date desc");
		List<LLNotice> list = llNoticeService.selectByWhere(parameters);
		long size = llNoticeService.selectCountByWhere(parameters);
		return new ResultRowsInfo(list, size);
	}

	/**
	 * 推荐套餐
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("tuijian.do")
	public ResultInfo tuijian(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (userinfo == null) {
			BusinessExceptionUtil.threwException("找不到该用户");
		}

		Map<String, Object> parameters = new HashMap<>();
		MapUtils.putPageInfo(parameters, json);
		parameters.put("tuijian", 1);
		parameters.put("orderby", "create_date");
		List<LLMealInfo> list = llMealInfoService.selectByWhere(parameters);
		long size = llMealInfoService.selectCountByWhere(parameters);
		return new ResultRowsInfo(list, size);
	}

	// 首页部分开始
	/**
	 * 基础流量
	 */
	@ResponseBody
	@RequestMapping("baseliuliang.do")
	public ResultInfo baseliuliang(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		int total = llMealSaleService.getBaseTotalSY(userinfo.getId());
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS, total);
	}

	/**
	 * 套餐流量
	 */
	@ResponseBody
	@RequestMapping("mealliuliang.do")
	public ResultInfo mealliuliang(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		int total = llMealSaleService.getMealTotalSY(userinfo.getId());
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS, total);
	}

	/**
	 * 最近失效套餐
	 */
	@ResponseBody
	@RequestMapping("meallastone.do")
	public ResultInfo meallastone(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		LLMealSale mealsale = llMealSaleService.getSY(userinfo.getId());
		if (mealsale != null && mealsale.getType() == 2) {
			mealsale = null;
		}
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS, mealsale);
	}

	/**
	 * 套餐流量
	 */
	@ResponseBody
	@RequestMapping("totalliuliang.do")
	public ResultInfo totalliuliang(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		int total = llMealSaleService.getTotalSY(userinfo.getId(), null);
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS, total);
	}

	/**
	 * 累计购买流量
	 */
	@ResponseBody
	@RequestMapping("leijiliuliang.do")
	public ResultInfo leijiliuliang(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		Long count = llCaiGouInfoService.getLeiJiLiuliang(userinfo.getId());
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS, count);
	}

	/**
	 * 累计订单-基本流量
	 */
	@ResponseBody
	@RequestMapping("salebase.do")
	public ResultInfo salebase(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		int count = llMealSaleService.getBaseLeiJi(userinfo.getId());
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS, count);
	}

	/**
	 * 累计订单-套餐
	 */
	@ResponseBody
	@RequestMapping("salemeal.do")
	public ResultInfo salemeal(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		int count = llMealSaleService.getMealLeiJi(userinfo.getId());
		return new ResultInfo(ResultType.SUCCESS, Const.RESUME_SUCCESS, count);
	}

	// 首页部分结束
}
