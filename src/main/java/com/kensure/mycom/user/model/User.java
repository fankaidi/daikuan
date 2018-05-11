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

import java.util.Date;

import co.kensure.frame.BaseInfo;

/**
 * 用户主要信息对象类
 * @author fankd created on 2018-5-8
 * @since
 */
public class User extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/**用户id*/		
	private Long id;

	/**用户名称*/		
	private String name;

	/**用户密码*/		
	private String pwd;

	/**电话号码*/		
	private String mobile;

	/**昵称*/		
	private String nickname;

	/**登录名称*/		
	private String loginname;

	/**身份证*/		
	private String card;

	/**创建时间*/		
	private Date createDate;

	/**修改时间*/		
	private Date updateDate;

	/**状态*/		
	private Integer status;

	/**企业号*/		
	private Integer epid;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
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
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getEpid() {
		return epid;
	}

	public void setEpid(Integer epid) {
		this.epid = epid;
	}
}
