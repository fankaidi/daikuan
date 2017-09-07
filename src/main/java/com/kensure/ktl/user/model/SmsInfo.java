/*
 * 文件名称: SmsInfo.java
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
 * 验证码对象类
 * @author fankd created on 2017-9-6
 * @since
 */
public class SmsInfo extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/**手机号码*/		
	private String mobile; 

	/**用id*/		
	private Long userid; 

	/**验证码*/		
	private String qrcode; 

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
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
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
