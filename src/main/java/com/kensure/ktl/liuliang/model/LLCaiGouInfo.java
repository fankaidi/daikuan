/*
 * 文件名称: LLCaiGouInfo.java
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
 * 采购流量详细信息对象类
 * @author fankd created on 2018-3-9
 * @since
 */
public class LLCaiGouInfo extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/**流量id*/		
	private Long baseId;

	/**流量源id*/		
	private Long baseSourceId;

	/**采购用户id*/		
	private Long userId;

	/**是否套餐*/		
	private Integer ismeal;

	/**消费id*/		
	private Long saleId;

	/**金额*/		
	private String jine;

	/**会话id*/		
	private String sessionid;

	/***/		
	private Date createDate;
	
	/***/		
	private String createDateStr;

	/***/		
	private Date updateDate;

	/**状态 1是有效*/		
	private Integer status;

	/**
	 * 采购的流量信息
	 */
	private LLBaseInfo llBaseInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getBaseId() {
		return baseId;
	}

	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}
	public Long getBaseSourceId() {
		return baseSourceId;
	}

	public void setBaseSourceId(Long baseSourceId) {
		this.baseSourceId = baseSourceId;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getIsmeal() {
		return ismeal;
	}

	public void setIsmeal(Integer ismeal) {
		this.ismeal = ismeal;
	}
	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}
	public String getJine() {
		return jine;
	}

	public void setJine(String jine) {
		this.jine = jine;
	}
	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
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

	public LLBaseInfo getLlBaseInfo() {
		return llBaseInfo;
	}

	public void setLlBaseInfo(LLBaseInfo llBaseInfo) {
		this.llBaseInfo = llBaseInfo;
	}

	public String getCreateDateStr() {
		this.createDateStr = DateUtils.format(this.createDate);
		return this.createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
}
