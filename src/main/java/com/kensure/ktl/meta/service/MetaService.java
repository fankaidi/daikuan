package com.kensure.ktl.meta.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import co.kensure.conn.ConnUtils;
import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.frame.JSBaseService;
import co.kensure.mem.DateUtils;

import com.kensure.ktl.meta.model.Column;
import com.kensure.ktl.meta.model.Table;

/**
 * 数据表结构分析工具类
 * 
 * @author fankd
 * @since SHK Framework 1.0
 */
@Service
public class MetaService extends JSBaseService {

	@Resource
	private DataSource dataSource;

	private final static Logger LOGGER = Logger.getLogger(MetaService.class);

	private final static String getTableSql = " SELECT tablename FROM pg_tables WHERE schemaname = ? ORDER BY tablename";
	private final static String getColumnSql = "SELECT table_name,column_name,ordinal_position,is_nullable,data_type,character_maximum_length,datetime_precision "
			+ "from information_schema.columns where table_schema = ? and table_name = ? ";

	public static void main(String[] args) {
		String d = "1111-11-11 11:11:11";
		Date dd = DateUtils.parse(d, DateUtils.DATE_FORMAT_PATTERN);
		System.out.print("aa=" + dd.getTime());
	}

	/**
	 * 获取schema下的所有表名
	 * 
	 * @param schema
	 */
	public List<String> getTableNames(String schema) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<String> tableNameList = new ArrayList<>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getTableSql);
			pstmt.setString(1, schema);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String tablename = rs.getString("tablename");
				tableNameList.add(tablename);
			}
		} catch (SQLException e) {
			BusinessExceptionUtil.threwException(e);
		} finally {
			ConnUtils.close(pstmt, conn);
		}
		return tableNameList;
	}

	/**
	 * 获取表下的字段结构
	 * 
	 * @param schema
	 * @param tableName
	 */
	public List<Column> getTableColumns(String schema, String tableName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<Column> columnList = new ArrayList<>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getColumnSql);
			pstmt.setString(1, schema);
			pstmt.setString(2, tableName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String tName = rs.getString("table_name");
				String columnName = rs.getString("column_name");
				Integer ordinalPosition = rs.getInt("ordinal_position");
				Boolean nullAble = rs.getBoolean("is_nullable");
				String dataType = rs.getString("data_type");
				Integer varcharlength = rs.getInt("character_maximum_length");
				Integer dp = rs.getInt("datetime_precision");
				Column column = new Column();
				column.setColumnName(columnName);
				column.setTableName(tName);
				column.setOrdinalPosition(ordinalPosition);
				column.setNullAble(nullAble);
				column.setDataType(dataType);
				column.setVarcharLength(varcharlength);
				column.setDateTimeLength(dp);
				columnList.add(column);
			}
		} catch (SQLException e) {
			BusinessExceptionUtil.threwException(e);
		} finally {
			ConnUtils.close(pstmt, conn);
		}
		return columnList;
	}

	/**
	 * 获取schema下的所有表名
	 * 
	 * @param schema
	 */
	public Table getTable(String schema,String tableName) {
		Table tb = new Table();
		tb.setTableName(tableName);
		List<Column> columns = getTableColumns(schema, tableName);
		tb.setColumns(columns);
		return tb;
	}

}
