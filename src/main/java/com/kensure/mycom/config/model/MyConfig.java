/*
 * 文件名称: MyConfig.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-5-8
 * 修改内容: 
 */
package com.kensure.mycom.config.model;

import co.kensure.frame.BaseInfo;;

/**
 * 配置信息对象类
 * @author fankd created on 2018-5-8
 * @since
 */
public class MyConfig extends BaseInfo{

	private static final long serialVersionUID = 3545276994084105527L;
	
	/***/		
	private String id;

	/***/		
	private String name;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
