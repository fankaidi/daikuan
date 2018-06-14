package com.kensure.ktl.meta.model;

import java.util.List;

import co.kensure.frame.BaseInfo;

/**
 * 表中的列对象类
 * 
 * @author fankd created on 2017-9-29
 * @since
 */
public class Table extends BaseInfo {

	private static final long serialVersionUID = 3545276994084105527L;

	/** table_name表名 */
	private String tableName;

	/** 列数据 */
	private List<Column> columns;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
}
