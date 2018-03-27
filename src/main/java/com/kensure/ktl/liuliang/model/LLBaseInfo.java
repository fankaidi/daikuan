/*
 * 文件名称: LLBaseInfo.java
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
import co.kensure.mem.DateUtils;

/**
 * 流量基础信息表对象类
 * @author fankd created on 2018-3-9
 * @since
 */
public class LLBaseInfo extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/**源id*/		
	private Long sourceId;

	/**名称*/		
	private String name;

	/**手机号*/		
	private String mobile;

	/**qq号*/		
	private String qq;

	/**微信号*/		
	private String weixin;

	/**性别*/		
	private String xb;

	/**身份证*/		
	private String card;

	/**年龄*/		
	private Integer year;

	/**所在区域*/		
	private String area;

	/**设备*/		
	private String shebei;

	/**渠道*/		
	private String qudao;

	/**单位*/		
	private String danwei;

	/**利息*/		
	private String lixi;

	/**借款金额*/		
	private String jkfs;

	/**还款金额*/		
	private String hkje;

	/**负债情况*/		
	private String fuzai;

	/**芝麻分*/		
	private Integer zhimafen;

	/**花呗额度*/		
	private Integer huabeiedu;

	/**借呗额度*/		
	private Integer jiebeiedu;

	/**信用卡额度*/		
	private Integer xinyongka;

	/**借贷宝额度*/		
	private Integer jiedaibao;

	/**流量进来是哪一天*/		
	private String day;

	/***/		
	private Date createDate;

	/***/		
	private Date updateDate;

	/**借款申请时间*/		
	private Date publishDate;
	
	/**借款申请时间字符串格式*/		
	private String publishDateStr;

	/**状态1是有效的,-1是失效*/		
	private Integer status;
	
	/**失效次数*/		
	private Integer expireCount;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
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
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	public String getShebei() {
		return shebei;
	}

	public void setShebei(String shebei) {
		this.shebei = shebei;
	}
	public String getQudao() {
		return qudao;
	}

	public void setQudao(String qudao) {
		this.qudao = qudao;
	}
	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}
	public String getLixi() {
		return lixi;
	}

	public void setLixi(String lixi) {
		this.lixi = lixi;
	}
	public String getJkfs() {
		return jkfs;
	}

	public void setJkfs(String jkfs) {
		this.jkfs = jkfs;
	}
	public String getHkje() {
		return hkje;
	}

	public void setHkje(String hkje) {
		this.hkje = hkje;
	}
	public String getFuzai() {
		return fuzai;
	}

	public void setFuzai(String fuzai) {
		this.fuzai = fuzai;
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
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
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
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPublishDateStr() {
		this.publishDateStr = DateUtils.format(this.publishDate);
		return this.publishDateStr;
	}

	public void setPublishDateStr(String publishDateStr) {
		this.publishDateStr = publishDateStr;
	}

	public Integer getExpireCount() {
		return expireCount;
	}

	public void setExpireCount(Integer expireCount) {
		this.expireCount = expireCount;
	}
	
}
