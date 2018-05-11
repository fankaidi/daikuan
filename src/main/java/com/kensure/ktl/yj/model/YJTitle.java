/*
 * 文件名称: YJTitle.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-4-19
 * 修改内容: 
 */
package com.kensure.ktl.yj.model;

import java.util.Date;

import co.kensure.frame.BaseInfo;

/**
 * 游记主表对象类
 * @author fankd created on 2018-4-19
 * @since
 */
public class YJTitle extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/***/		
	private String name;

	/***/		
	private String pic;

	/***/		
	private Integer dorder;

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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getDorder() {
		return dorder;
	}

	public void setDorder(Integer dorder) {
		this.dorder = dorder;
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
