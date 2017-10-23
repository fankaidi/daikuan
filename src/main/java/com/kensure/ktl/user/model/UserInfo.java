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

	/**职位*/		
	private String work;
	
	/**住哪里*/
	private String home;
	

	private String mobiletime;
	private String mobilenum;
	private String isrealname;
	private String wyfz;
	private String wuyh;
	private String mffz;
	private String mfyh;
	private String txyfz;
	private String txyyh;
	private String jdbfz;
	private String jdbyh;
	private String jjdfz;
	private String jjdyh;
	private String qtptfz;
	private String qtptyh;
	private String jrhk;
	private String ywyqjl;
	private String ywcf;
	private String ywsb;
	private String ywgjj;
	private String ywxhk;

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

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getMobiletime() {
		return mobiletime;
	}

	public void setMobiletime(String mobiletime) {
		this.mobiletime = mobiletime;
	}

	public String getMobilenum() {
		return mobilenum;
	}

	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}

	public String getIsrealname() {
		return isrealname;
	}

	public void setIsrealname(String isrealname) {
		this.isrealname = isrealname;
	}

	public String getWyfz() {
		return wyfz;
	}

	public void setWyfz(String wyfz) {
		this.wyfz = wyfz;
	}

	public String getWuyh() {
		return wuyh;
	}

	public void setWuyh(String wuyh) {
		this.wuyh = wuyh;
	}

	public String getMffz() {
		return mffz;
	}

	public void setMffz(String mffz) {
		this.mffz = mffz;
	}

	public String getMfyh() {
		return mfyh;
	}

	public void setMfyh(String mfyh) {
		this.mfyh = mfyh;
	}

	public String getTxyfz() {
		return txyfz;
	}

	public void setTxyfz(String txyfz) {
		this.txyfz = txyfz;
	}

	public String getTxyyh() {
		return txyyh;
	}

	public void setTxyyh(String txyyh) {
		this.txyyh = txyyh;
	}

	public String getJdbfz() {
		return jdbfz;
	}

	public void setJdbfz(String jdbfz) {
		this.jdbfz = jdbfz;
	}

	public String getJdbyh() {
		return jdbyh;
	}

	public void setJdbyh(String jdbyh) {
		this.jdbyh = jdbyh;
	}

	public String getJjdfz() {
		return jjdfz;
	}

	public void setJjdfz(String jjdfz) {
		this.jjdfz = jjdfz;
	}

	public String getJjdyh() {
		return jjdyh;
	}

	public void setJjdyh(String jjdyh) {
		this.jjdyh = jjdyh;
	}

	public String getQtptfz() {
		return qtptfz;
	}

	public void setQtptfz(String qtptfz) {
		this.qtptfz = qtptfz;
	}

	public String getQtptyh() {
		return qtptyh;
	}

	public void setQtptyh(String qtptyh) {
		this.qtptyh = qtptyh;
	}

	public String getJrhk() {
		return jrhk;
	}

	public void setJrhk(String jrhk) {
		this.jrhk = jrhk;
	}

	public String getYwyqjl() {
		return ywyqjl;
	}

	public void setYwyqjl(String ywyqjl) {
		this.ywyqjl = ywyqjl;
	}

	public String getYwcf() {
		return ywcf;
	}

	public void setYwcf(String ywcf) {
		this.ywcf = ywcf;
	}

	public String getYwsb() {
		return ywsb;
	}

	public void setYwsb(String ywsb) {
		this.ywsb = ywsb;
	}

	public String getYwgjj() {
		return ywgjj;
	}

	public void setYwgjj(String ywgjj) {
		this.ywgjj = ywgjj;
	}

	public String getYwxhk() {
		return ywxhk;
	}

	public void setYwxhk(String ywxhk) {
		this.ywxhk = ywxhk;
	}
	
	
}
