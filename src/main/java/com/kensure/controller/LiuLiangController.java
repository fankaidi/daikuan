package com.kensure.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.kensure.http.RequestUtils;

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.user.service.LoanMoneyService;
import com.kensure.ktl.user.service.SmsInfoService;
import com.kensure.ktl.user.service.UserInfoService;
import com.kensure.ktl.user.service.UserLoginService;

/**
 * 流量业务的页面跳转
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "liuliang")
public class LiuLiangController {

	@Resource
	private UserInfoService userInfoService;

	@Resource
	private UserLoginService userLoginService;

	@Resource
	private LoanMoneyService loanMoneyService;

	@Resource
	private SmsInfoService smsInfoService;

	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/login.jsp";
	}
	
	/**
	 * 最新流量信息
	 * @return
	 */
	@RequestMapping("zhuce")
	public String zhuce(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/zhuce.jsp";
	}

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("index")
	public String index(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/index.jsp";
	}
	
	/**
	 * 后台流量信息页面
	 * @return
	 */
	@RequestMapping("baseinfo")
	public String baseinfo(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/adminbaseinfo.jsp";
	}
	
	/**
	 * 后台套餐销售列表页面
	 * @return
	 */
	@RequestMapping("mealsalelist")
	public String mealsalelist(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/mealsalelist.jsp";
	}
	
	/**
	 * 后台套餐销售页面
	 * @return
	 */
	@RequestMapping("mealsale")
	public String mealsale(HttpServletRequest req, HttpServletResponse rep, Model model) {
		JSONObject json = RequestUtils.paramToJson(req);
		String id = json.getString("id");
		model.addAttribute("id", id);
		return "liuliang/mealsale.jsp";
	}
	
	/**
	 * 后台套餐列表
	 * @return
	 */
	@RequestMapping("meallist")
	public String meallist(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/meallist.jsp";
	}
	

	/**
	 * 最新流量信息
	 * @return
	 */
	@RequestMapping("newbaseinfo")
	public String newbaseinfo(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/newbaseinfo.jsp";
	}
	
	/**
	 * 推荐套餐
	 * @return
	 */
	@RequestMapping("tuijian")
	public String tuijian(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/tuijianmeal.jsp";
	}
	
	/**
	 * 套餐新增页面
	 * @return
	 */
	@RequestMapping("mealadd")
	public String mealadd(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/mealadd.jsp";
	}
	
	/**
	 * 已购流量信息
	 * @return
	 */
	@RequestMapping("yigou")
	public String yigou(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/yigoubaseinfo.jsp";
	}
	
	/**
	 * 消费记录|订单查询
	 * @return
	 */
	@RequestMapping("xiaofei")
	public String xiaofei(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/xiaofei.jsp";
	}
	
	/**
	 * 最新通知
	 * @return
	 */
	@RequestMapping("newnotice")
	public String newnotice(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/newnotice.jsp";
	}
	
	/**
	 * 关于我们
	 * @return
	 */
	@RequestMapping("aboutus")
	public String aboutus(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/aboutus.jsp";
	}
	
	/**
	 * 修改用户密码
	 * @return
	 */
	@RequestMapping("updatepwd")
	public String updatepwd(HttpServletRequest req, HttpServletResponse rep, Model model) {
		return "liuliang/updatepwd.jsp";
	}
	

}
