/*
 * 文件名称: LLMealSale.java
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
 * 套餐订单信息对象类
 * @author fankd created on 2018-3-9
 * @since
 */
public class LLMealSale extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/**套餐id*/		
	private Long mealId;

	/**金额*/		
	private String jine;

	/**单价*/		
	private String danjia;
	
	/**销售分数*/		
	private Integer fenshu;

	/**总计条数*/		
	private Integer total;

	/**剩余条数*/		
	private Integer sytiaoshu;

	/**类型，1是条数固定的套餐，2是条数自由的套餐*/		
	private Integer type;

	/***/		
	private Date createDate;
	
	/***/		
	private String createDateStr;

	/***/		
	private Date updateDate;

	/**有效期*/		
	private Date validityDate;
	
	/***/		
	private String validityDateStr;

	
	/**购买人id*/		
	private Long userId;
	
	/***/		
	private String userName;
	
	/***/		
	private String mealName;	

	/**状态0是未付款，  1是有效，-1是失效*/		
	private Integer status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getMealId() {
		return mealId;
	}

	public void setMealId(Long mealId) {
		this.mealId = mealId;
	}
	public String getJine() {
		return jine;
	}

	public void setJine(String jine) {
		this.jine = jine;
	}
	public String getDanjia() {
		return danjia;
	}

	public void setDanjia(String danjia) {
		this.danjia = danjia;
	}
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getSytiaoshu() {
		return sytiaoshu;
	}

	public void setSytiaoshu(Integer sytiaoshu) {
		this.sytiaoshu = sytiaoshu;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getFenshu() {
		return fenshu;
	}

	public void setFenshu(Integer fenshu) {
		this.fenshu = fenshu;
	}

	public String getCreateDateStr() {
		this.createDateStr = DateUtils.format(this.createDate);
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getValidityDateStr() {
		this.validityDateStr = DateUtils.format(this.validityDate);
		return validityDateStr;
	}

	public void setValidityDateStr(String validityDateStr) {
		this.validityDateStr = validityDateStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

}
