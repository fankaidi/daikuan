/*
 * 文件名称: MyUserLogin.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-5-8
 * 修改内容: 
 */
package com.kensure.mycom.user.model;

import co.kensure.frame.BaseInfo;

/**
 * 用户会话信息
 * @author fankd created on 2018-5-8
 * @since
 */
public class UserSession extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private String sessionid;
	
	private String openId;
	/**
	 * 渠道中的名称
	 */
	private String name;
	
	/**
	 * 真实名称
	 */
	private String realname;
	
	/**
	 * 昵称
	 */
	private String nickname;

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
