package com.kensure.controller.user.data;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowsInfo;

import com.kensure.mycom.user.model.User;
import com.kensure.mycom.user.service.UserService;

/**
 * 用户逻辑处理类
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "user")
public class UserPostController {



	@Resource
	private UserService userService;

	/**
	 * 微信登录类，根据获取的open
	 */
	@ResponseBody
	@RequestMapping(value = "list.do", produces = "application/json;charset=UTF-8")
	public ResultInfo bkClear(HttpServletRequest req, HttpServletResponse rep) {
		List<User> list = userService.selectAll();
		return new ResultRowsInfo(list);
	}
}
