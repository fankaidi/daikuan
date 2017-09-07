/*
 * 文件名称: UserInfo.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-6
 * 修改内容: 
 */
package com.kensure.ktl.user.model;

import java.util.Date;

import co.kensure.frame.BaseInfo;

/**
 * 用户信息表对象类
 * @author fankd created on 2017-9-6
 * @since
 */
public class UserInfo extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/**名字*/		
	private String name; 

	/**密码*/		
	private String pwd; 

	/**手机号码*/		
	private String mobile; 

	/**qq号码*/		
	private String qq; 

	/**性别*/		
	private String xb; 

	/**身份证*/		
	private String card; 

	/**年龄*/		
	private Integer year; 

	/**单位*/		
	private String danwei; 

	/**职务*/		
	private String zhiwu; 

	/**电话*/		
	private String phone; 

	/**芝麻分*/		
	private Integer zhimafen; 

	/**花呗额度*/		
	private Integer huabeiedu; 

	/**借呗额度*/		
	private Integer jiebeiedu; 

	/**信用卡*/		
	private Integer xinyongka; 

	/**借贷宝*/		
	private Integer jiedaibao; 

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
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
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
	public String getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getZhimafen() {
		return zhimafen;
	}

	public void setZhimafen(Integer zhimafen) {
		this.zhimafen = zhimafen;
	}
	public Integer getHuabeiedu() {
		return huabeiedu;
	}

	public void setHuabeiedu(Integer huabeiedu) {
		this.huabeiedu = huabeiedu;
	}
	public Integer getJiebeiedu() {
		return jiebeiedu;
	}

	public void setJiebeiedu(Integer jiebeiedu) {
		this.jiebeiedu = jiebeiedu;
	}
	public Integer getXinyongka() {
		return xinyongka;
	}

	public void setXinyongka(Integer xinyongka) {
		this.xinyongka = xinyongka;
	}
	public Integer getJiedaibao() {
		return jiedaibao;
	}

	public void setJiedaibao(Integer jiedaibao) {
		this.jiedaibao = jiedaibao;
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
