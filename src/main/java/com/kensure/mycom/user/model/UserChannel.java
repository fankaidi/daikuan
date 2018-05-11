/*
 * 文件名称: UserChannel.java
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
 * 用户渠道信息对象类
 * @author fankd created on 2018-5-8
 * @since
 */
public class UserChannel extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/**渠道流水号*/		
	private Long id;

	/**名称*/		
	private String name;

	/**微信用户在应用下的openid*/		
	private String openid;

	/**微信用户在企业id*/		
	private String unitid;

	/**创建时间*/		
	private Date createDate;

	/**修改时间*/		
	private Date updateDate;

	/**用户id*/		
	private Long userid;

	/**渠道id,1表示是某个小程序*/		
	private Integer channelid;


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
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
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
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}
}
