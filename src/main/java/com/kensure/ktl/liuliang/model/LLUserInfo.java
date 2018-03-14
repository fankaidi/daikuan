/*
 * 文件名称: LLUserInfo.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-3-9
 * 修改内容: 
 */
package com.kensure.ktl.liuliang.model;

import java.util.Date;

import co.kensure.frame.BaseInfo;

/**
 * 商户信息表对象类
 * @author fankd created on 2018-3-9
 * @since
 */
public class LLUserInfo extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/**用户名*/		
	private String name;

	/**密码*/		
	private String pwd;

	/**手机号码*/		
	private String mobile;

	/**商户手机号*/		
	private String qiyemobile;

	/**昵称*/		
	private String nickname;

	/**登录名称*/		
	private String loginname;

	/**身份证*/		
	private String card;

	/**年龄*/		
	private Integer year;

	/**单位*/		
	private String danwei;

	/***/		
	private Date createDate;

	/***/		
	private Date updateDate;

	/**状态，1是可用*/		
	private Integer status;


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
	public String getQiyemobile() {
		return qiyemobile;
	}

	public void setQiyemobile(String qiyemobile) {
		this.qiyemobile = qiyemobile;
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
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
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
	
	/**
	 * id小于零的，是超级管理员
	 * @return
	 */
	public boolean isAdmin() {	
		return this.id < 0;
	}
}
