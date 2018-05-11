package com.kensure.mycom.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.exception.ParamUtils;
import co.kensure.frame.JSBaseService;
import co.kensure.http.HttpUtils;

import com.alibaba.fastjson.JSONObject;
import com.kensure.mycom.user.model.ChannelDefine;
import com.kensure.mycom.user.model.MyUserLogin;
import com.kensure.mycom.user.model.MyWeixin;
import com.kensure.mycom.user.model.User;
import com.kensure.mycom.user.model.UserChannel;
import com.kensure.mycom.user.model.UserSession;
import com.kensure.mycom.user.model.UserWeixin;

/**
 * 用户主要信息服务实现类
 * 
 * @author fankd created on 2018-5-8
 * @since
 */
@Service
public class UserWeixinService extends JSBaseService {

	@Resource
	private UserService userService;

	@Resource
	private MyUserLoginService myUserLoginService;

	@Resource
	private UserChannelService userChannelService;

	@Resource
	private MyWeixinService myWeixinService;

	/**
	 * 微信用户登录类
	 * 
	 * @param parameters
	 * @return
	 */
	private UserWeixin weixinLogin(String code, ChannelDefine chd) {
		String appid = chd.getAppid();
		String sc = chd.getSecret();
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + sc + "&js_code=" + code + "&grant_type=authorization_code";
		String content = HttpUtils.getBody(url);
		UserWeixin weixin = JSONObject.parseObject(content, UserWeixin.class);
		return weixin;
	}

	/**
	 * 开始登录
	 * 
	 * @return
	 */
	public UserSession doLogin(String code, ChannelDefine chd) {
		// 先根据code获取openId
		UserWeixin openUser = weixinLogin(code, chd);
		String openId = openUser.getOpenid();

		// 查找渠道用户，如果没有，完善信息
		UserChannel ucs = userChannelService.selectByOpenId(openId, chd.getId());
		// 如果用户不存在
		if (ucs == null) {
			ucs = new UserChannel();
			ucs.setChannelid(chd.getId());
			ucs.setOpenid(openId);
			ucs.setUnitid(openUser.getUnitId());
			userChannelService.insert(ucs);
		}

		// 用渠道用户登录
		MyUserLogin userlogin = myUserLoginService.loginChannel(ucs);
		UserSession us = myUserLoginService.getUserSessionBySessionId(userlogin.getSessionid());
		return us;
	}

	/**
	 * 设置用户的信息,包括头像和昵称
	 * 
	 * @return
	 */
	public void addWeixin(String sessionid, MyWeixin myWeixin) {
		MyUserLogin login = myUserLoginService.selectBySessionId(sessionid);
		// 查找微信用户，如果没有，完善信息
		MyWeixin ucs = myWeixinService.selectOne(login.getChannelid());
		if (ucs == null) {
			// 插入微信用户信息
			ucs = myWeixin;
			ucs.setId(login.getChannelid());
			myWeixinService.insert(ucs);

			// 修改渠道里面的名称
			UserChannel uchannel = userChannelService.selectOne(login.getChannelid());
			uchannel.setName(myWeixin.getNickName());
			userChannelService.update(uchannel);
		}
	}

	/**
	 * 设置用户的信息,包括头像和昵称
	 * 
	 * @return
	 */
	public MyWeixin getWeixin(String sessionid) {
		MyUserLogin login = myUserLoginService.selectBySessionId(sessionid);
		// 查找微信用户，如果没有，完善信息
		MyWeixin ucs = myWeixinService.selectOne(login.getChannelid());
		return ucs;
	}
	
	/**
	 * 提交电话号码，创建真正的用户
	 * 
	 * @return
	 */
	public void mobileUser(String sessionid,String mobile) {
		ParamUtils.isBlankThrewException(mobile, "电话不能为空");
		
		//用户登录会话信息
		MyUserLogin login = myUserLoginService.selectBySessionId(sessionid);
		//用户渠道信息
		UserChannel userChannel = userChannelService.selectOne(login.getChannelid());
		if(userChannel.getUserid() == null){
			return;
		}
		//用户在微信上的信息
		MyWeixin ucs = myWeixinService.selectOne(login.getChannelid());
		
		User user = new User();
		user.setEpid(userChannel.getChannelid());
		user.setName(ucs.getNickName());
		user.setLoginname(mobile);
		user.setMobile(mobile);
		user.setNickname(ucs.getNickName());
		//插入用户信息
		userService.insert(user);
		
		//更改渠道关联信息
		userChannel.setUserid(user.getId());
		userChannelService.update(userChannel);

	}

}
