/*
 * 文件名称: UserLogin.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-5
 * 修改内容: 
 */
package com.kensure.ktl.user.model;

import java.util.Date;

import co.kensure.frame.BaseInfo;

/**
 * 用户登录信息对象类
 * @author fankd created on 2017-9-5
 * @since
 */
public class UserLogin extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/**会话id*/		
	private String sessionid; 

	/**用id*/		
	private Long userid; 

	/**ip*/		
	private String ip; 

	/**创建时间*/		
	private Date createDate; 

	/**修改时间*/		
	private Date updateDate; 


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
