package com.kensure.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.user.model.LoanMoney;
import com.kensure.ktl.user.model.UserInfo;
import com.kensure.ktl.user.model.UserLogin;
import com.kensure.ktl.user.service.LoanMoneyService;
import com.kensure.ktl.user.service.UserInfoService;
import com.kensure.ktl.user.service.UserLoginService;

@Controller
public class PageController {
	
	@Resource
	private UserInfoService userInfoService;
	
	@Resource
	private UserLoginService userLoginService;
	
	@Resource
	private LoanMoneyService loanMoneyService;
	
	

	
    @RequestMapping("home.do")
	public String home(HttpServletRequest req, HttpServletResponse rep,Model model) {
		try {	
			UserLogin userse = userLoginService.selectBySessionId(req.getSession().getId());
			if(userse != null){
				UserInfo user = userInfoService.selectOne(userse.getUserid());
				model.addAttribute("user", user);
			}
			return "page/index.jsp";
		}catch (Exception e) {	
			return "page/error.jsp";
		}
	}	
    
    @RequestMapping("help.do")
	public String help(HttpServletRequest req, HttpServletResponse rep,Model model) {
		try {
			UserLogin userse = userLoginService.selectBySessionId(req.getSession().getId());
			if(userse != null){
				UserInfo user = userInfoService.selectOne(userse.getUserid());
				model.addAttribute("user", user);
			}
			return "page/help.jsp";
		}catch (Exception e) {	
			return "page/error.jsp";
		}
	}

    @RequestMapping("aboutus.do")
	public String aboutus(HttpServletRequest req, HttpServletResponse rep,Model model) {
		try {
			UserLogin userse = userLoginService.selectBySessionId(req.getSession().getId());
			if(userse != null){
				UserInfo user = userInfoService.selectOne(userse.getUserid());
				model.addAttribute("user", user);
			}
			return "page/aboutus.jsp";
		}catch (Exception e) {	
			return "page/error.jsp";
		}
	}
    
    /**
     * 用户注册页面
     * @param req
     * @param rep
     * @param model
     * @return
     */
    @RequestMapping("adduser.do")
   	public String adduser(HttpServletRequest req, HttpServletResponse rep,Model model) {
   		try {	
   			return "page/adduser.jsp";
   		}catch (Exception e) {	
   			return "page/error.jsp";
   		}
   	}
    
    /**
     * 申请贷款
     * @param req
     * @param rep
     * @param model
     * @return
     */
    @RequestMapping("addmoney.do")
   	public String addmoney(HttpServletRequest req, HttpServletResponse rep,Model model) {
   		try {	
   			UserLogin userse = userLoginService.selectBySessionId(req.getSession().getId());
			if(userse != null){
				UserInfo user = userInfoService.selectOne(userse.getUserid());
				model.addAttribute("user", user);
			}else{
				rep.sendRedirect("/login.do");
			}
   			return "page/addmoney.jsp";
   		}catch (Exception e) {	
   			return "page/error.jsp";
   		}
   	}
    
    /**
     * 查看贷款记录
     * @param req
     * @param rep
     * @param model
     * @return
     */
    @RequestMapping("moneylist.do")
   	public String moneylist(HttpServletRequest req, HttpServletResponse rep,Model model) {
   		try {	
   			UserLogin userse = userLoginService.selectBySessionId(req.getSession().getId());
			if(userse != null){
				UserInfo user = userInfoService.selectOne(userse.getUserid());
				model.addAttribute("user", user);
			}
   			return "page/moneylist.jsp";
   		}catch (Exception e) {	
   			return "page/error.jsp";
   		}
   	}
    
    /**
     * 用户登录
     * @param req
     * @param rep
     * @param model
     * @return
     */
    @RequestMapping("login.do")
   	public String login(HttpServletRequest req, HttpServletResponse rep,Model model) {
   		try {	
   			return "page/login.jsp";
   		}catch (Exception e) {	
   			return "page/error.jsp";
   		}
   	}
    
    /**
     * 用户登录
     * @param req
     * @param rep
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("userlogin.do")
   	public ResultInfo userlogin(HttpServletRequest req, HttpServletResponse rep) {
    	JSONObject json = RequestUtils.paramToJson(req);
    	String username = json.getString("username");
    	String password = json.getString("password");
    	Map<String, Object> map = MapUtils.genMap("mobile",username);
    	List<UserInfo> list = userInfoService.selectByWhere(map);
    	if(CollectionUtils.isEmpty(list)){
    		BusinessExceptionUtil.threwException("用户不存在，请注册");
    	}
    	UserInfo user = list.get(0);
    	if(!user.getPwd().equalsIgnoreCase(password)){
    		BusinessExceptionUtil.threwException("密码错误!");
    	}
    	UserLogin userLogin = new UserLogin();
    	Date date = new Date();
    	userLogin.setId(date.getTime());
		userLogin.setCreateDate(date);
		userLogin.setUpdateDate(date);
		userLogin.setUserid(user.getId());
		userLogin.setSessionid(req.getSession().getId());
		userLogin.setIp(req.getRemoteHost());
    	userLoginService.insert(userLogin);
    	return new ResultRowInfo();
    	
   	}
    
    
    /**
     * 用户注册数据保存
     * @param req
     * @param rep
     * @param model
     * @return
     */
    @ResponseBody 
    @RequestMapping(value = "commituser.do", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
   	public ResultInfo adduser(HttpServletRequest req, HttpServletResponse rep) {
    	JSONObject json = RequestUtils.paramToJson(req);
		UserInfo user = JSONObject.parseObject(json.toJSONString(), UserInfo.class);
		Date date = new Date();
		user.setId(date.getTime());
		user.setCreateDate(date);
		user.setUpdateDate(date);
		userInfoService.insert(user);	
		return new ResultRowInfo();
   	}
    
    /**
     * 增加借款记录
     * @param req
     * @param rep
     * @param model
     * @return
     */
    @ResponseBody 
    @RequestMapping(value = "commitmoney.do", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json;charset=UTF-8")
   	public ResultInfo commitmoney(HttpServletRequest req, HttpServletResponse rep) {
    	JSONObject json = RequestUtils.paramToJson(req);
    	String money = json.getString("money");
    	
    	UserLogin userse = userLoginService.selectBySessionId(req.getSession().getId());
    	LoanMoney m = new LoanMoney();
		Date date = new Date();
		m.setId(date.getTime());
		m.setCreateDate(date);
		m.setUpdateDate(date);
		m.setMoney(NumberUtils.parseInteger(money, null));
		m.setUserid(userse.getUserid());
		loanMoneyService.insert(m);
		return new ResultRowInfo();
   	}
  
}
