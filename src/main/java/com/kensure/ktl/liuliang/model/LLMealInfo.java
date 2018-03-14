/*
 * 文件名称: LLMealInfo.java
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
 * 套餐信息对象类
 * @author fankd created on 2018-3-9
 * @since
 */
public class LLMealInfo extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/**套餐名称*/		
	private String name;

	/**类型，1是条数固定的套餐，2是固定单价的套餐*/	
	private Integer type;

	/**单价*/		
	private String danjia;

	/**金额*/		
	private String jine;

	/**条数*/		
	private Integer tiaoshu;

	/***/		
	private Date createDate;

	/***/		
	private Date updateDate;

	/**有效期，一般指 月数量*/		
	private String youxiaoqi;

	/**状态 1是有效*/		
	private Integer status;
	
	/**是否推荐，0是不推荐，1是推荐*/	
	private Integer tuijian;


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
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public String getDanjia() {
		return danjia;
	}

	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}
	public String getJine() {
		return jine;
	}

	public void setJine(String jine) {
		this.jine = jine;
	}
	public Integer getTiaoshu() {
		return tiaoshu;
	}

	public void setTiaoshu(Integer tiaoshu) {
		this.tiaoshu = tiaoshu;
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
	public String getYouxiaoqi() {
		return youxiaoqi;
	}

	public void setYouxiaoqi(String youxiaoqi) {
		this.youxiaoqi = youxiaoqi;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTuijian() {
		return tuijian;
	}

	public void setTuijian(Integer tuijian) {
		this.tuijian = tuijian;
	}
	
}
