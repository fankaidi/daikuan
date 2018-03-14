/*
 * 文件名称: BaseKey.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-3-13
 * 修改内容: 
 */
package com.kensure.ktl.base.model;

import java.util.Date;

import co.kensure.frame.BaseInfo;

/**
 * 主键表对象类
 * @author fankd created on 2018-3-13
 * @since
 */
public class BaseKey extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private String id;

	/***/		
	private String nowid;

	/***/		
	private Date createDate;

	/***/		
	private Date updateDate;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getNowid() {
		return nowid;
	}

	public void setNowid(String nowid) {
		this.nowid = nowid;
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
