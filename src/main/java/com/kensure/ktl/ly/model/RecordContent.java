/*
 * 文件名称: RecordContent.java
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

import co.kensure.frame.BaseInfo;;

/**
 * 发布记录大文本内容对象类
 * @author fankd created on 2017-9-29
 * @since
 */
public class RecordContent extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private Long id;

	/***/		
	private String content;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
