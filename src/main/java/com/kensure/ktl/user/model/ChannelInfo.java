/*
 * 文件名称: ChannelInfo.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-10-12
 * 修改内容: 
 */
package com.kensure.ktl.user.model;

import java.util.Date;

import co.kensure.frame.BaseInfo;

/**
 * 渠道信息对象类
 * @author fankd created on 2017-10-12
 * @since
 */
public class ChannelInfo extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/***/		
	private String cip;

	/***/		
	private String dip;

	/***/		
	private String agentno;

	/***/		
	private Integer cid;

	/***/		
	private String mobile;

	/***/		
	private String refurl;

	/***/		
	private Integer status;

	/***/		
	private Date createDate;

	/***/		
	private Date updateDate;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}
	public String getDip() {
		return dip;
	}

	public void setDip(String dip) {
		this.dip = dip;
	}
	public String getAgentno() {
		return agentno;
	}

	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
