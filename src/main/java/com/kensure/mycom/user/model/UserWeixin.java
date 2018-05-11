/*
 * 文件名称: User.java
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
 * 微信返回的对象
 * 
 * @author fankd created on 2018-5-8
 * @since
 */
public class UserWeixin extends BaseInfo {

	private static final long serialVersionUID = 3545276994084105527L;

	/** 应用下用户id */
	private String openid;

	/** 企业下id */
	private String unitId;
	
	private String session_key;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openId) {
		this.openid = openId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}

}
