package com.kensure.ktl.meta.model;

import co.kensure.frame.BaseInfo;

/**
 * 表中的列对象类
 * 
 * @author fankd created on 2017-9-29
 * @since
 */
public class Column extends BaseInfo {

	private static final long serialVersionUID = 3545276994084105527L;

	/** table_name表名 */
	private String tableName;

	/** column_name列名 */
	private String columnName;

	/** ordinal_position列位置 */
	private Integer ordinalPosition;

	/** is_nullable可否为空 */
	private Boolean nullAble;
	
	/** 是否主键 */
	private Boolean key;

	/** 数据类型data_type */
	private String dataType;

	/** character_maximum_length字符串长度 */
	private Integer varcharLength;

	/** datetime_precision 时间长度 */
	private Integer dateTimeLength;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public Boolean getNullAble() {
		return nullAble;
	}

	public void setNullAble(Boolean nullAble) {
		this.nullAble = nullAble;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getVarcharLength() {
		return varcharLength;
	}

	public void setVarcharLength(Integer varcharLength) {
		this.varcharLength = varcharLength;
	}

	public Integer getDateTimeLength() {
		return dateTimeLength;
	}

	public void setDateTimeLength(Integer dateTimeLength) {
		this.dateTimeLength = dateTimeLength;
	}

	public Boolean getKey() {
		return key;
	}

	public void setKey(Boolean key) {
		this.key = key;
	}

}
