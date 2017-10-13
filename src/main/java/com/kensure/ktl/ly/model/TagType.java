/*
 * 文件名称: TagType.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-29
 * 修改内容: 
 */
package com.kensure.ktl.ly.model;

import java.util.Date;

import co.kensure.frame.BaseInfo;

/**
 * 标签类型对象类
 * @author fankd created on 2017-9-29
 * @since
 */
public class TagType extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/***/		
	private String name;

	/***/		
	private String desc0;

	/***/		
	private String desc1;

	/***/		
	private String desc2;

	/***/		
	private String desc3;

	/***/		
	private String desc4;

	/***/		
	private String desc5;

	/***/		
	private String desc6;

	/***/		
	private String desc7;

	/***/		
	private String desc8;

	/***/		
	private String desc9;

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
	public String getDesc0() {
		return desc0;
	}

	public void setDesc0(String desc0) {
		this.desc0 = desc0;
	}
	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}
	public String getDesc4() {
		return desc4;
	}

	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}
	public String getDesc5() {
		return desc5;
	}

	public void setDesc5(String desc5) {
		this.desc5 = desc5;
	}
	public String getDesc6() {
		return desc6;
	}

	public void setDesc6(String desc6) {
		this.desc6 = desc6;
	}
	public String getDesc7() {
		return desc7;
	}

	public void setDesc7(String desc7) {
		this.desc7 = desc7;
	}
	public String getDesc8() {
		return desc8;
	}

	public void setDesc8(String desc8) {
		this.desc8 = desc8;
	}
	public String getDesc9() {
		return desc9;
	}

	public void setDesc9(String desc9) {
		this.desc9 = desc9;
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
