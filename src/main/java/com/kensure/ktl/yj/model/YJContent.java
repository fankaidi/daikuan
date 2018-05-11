/*
 * 文件名称: YJContent.java
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

import co.kensure.frame.BaseInfo;;

/**
 * 游记内容表对象类
 * @author fankd created on 2018-4-19
 * @since
 */
public class YJContent extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/***/		
	private Long titleId;

	/***/		
	private Integer type;

	/***/		
	private String content;

	/***/		
	private Integer dorder;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getTitleId() {
		return titleId;
	}

	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Integer getDorder() {
		return dorder;
	}

	public void setDorder(Integer dorder) {
		this.dorder = dorder;
	}
}
