/*
 * 文件名称: BaseKeyServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-3-13
 * 修改内容: 
 */
package com.kensure.mycom.base.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import co.kensure.conn.ConnUtils;
import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.frame.JSBaseService;
import co.kensure.mem.NumberUtils;

import com.kensure.mycom.base.dao.BaseKeyDao;
import com.kensure.mycom.base.model.BaseKey;

/**
 * 主键表服务实现类
 * 
 * @author fankd created on 2018-3-13
 * @since
 */
@Service
public class BaseKeyService extends JSBaseService {

	private final static Logger LOGGER = Logger.getLogger(BaseKeyService.class);

	private final static String updateSQL = " update base_key set nowid=? ,update_date=? where id=?";
	private final static String insertSQL = " insert into base_key (id,nowid,create_date,update_date) values(?,?,?,?)";

	@Resource
	private BaseKeyDao dao;

	@Resource
	private DataSource dataSource;

	public BaseKey selectOne(String id) {
		return dao.selectOne(id);
	}

	public List<BaseKey> selectByIds(Collection<Long> ids) {
		return dao.selectByIds(ids);
	}

	public List<BaseKey> selectAll() {
		return dao.selectAll();
	}

	public List<BaseKey> selectByWhere(Map<String, Object> parameters) {
		return dao.selectByWhere(parameters);
	}

	public long selectCount() {
		return dao.selectCount();
	}

	public long selectCountByWhere(Map<String, Object> parameters) {
		return dao.selectCountByWhere(parameters);
	}

	public boolean insert(BaseKey obj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(insertSQL);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pstmt.setString(1, obj.getId());
			pstmt.setString(2, obj.getNowid());
			pstmt.setTimestamp(3, time);
			pstmt.setTimestamp(4, time);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
			BusinessExceptionUtil.threwException(e);
		} finally {
			ConnUtils.close(pstmt, conn);
		}
		return true;
	}

	/**
	 * 不和业务保存事物一致性，自己管理连接
	 * 
	 * @param obj
	 * @return
	 */
	public boolean update(BaseKey obj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateSQL);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			pstmt.setString(1, obj.getNowid());
			pstmt.setTimestamp(2, time);
			pstmt.setString(3, obj.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
			BusinessExceptionUtil.threwException(e);
		} finally {
			ConnUtils.close(pstmt, conn);
		}
		return true;
	}

	/**
	 * 根据表名获取 主键
	 * 
	 * @param tableName
	 * @return
	 */
	public Long getKey(String tableName) {
		// 优先从缓存里面拿，没有就从数据库拿
		Long key = getKeyCache(tableName);
		if (key == null) {
			key = getKeyTable(tableName);
		}
		return key;
	}
	
	
	public void clearCache(){
		keyMap.clear();
	}

	private static Map<String, Long> keyMap = new HashMap<>();

	/**
	 * 优先从缓存里面取
	 * 
	 * @return
	 */
	private synchronized Long getKeyCache(String tableName) {
		Long id = keyMap.get(tableName);
		if (NumberUtils.isZero(id)) {
			return null;
		} else {
			Long nextId = id + 1;
			// 100的倍数，就需要修改数据库，让他加100;
			if (nextId % 100 == 0) {
				BaseKey bk = new BaseKey();
				bk.setId(tableName);
				bk.setNowid((nextId + 100) + "");
				update(bk);
			}
			keyMap.put(tableName, nextId);
		}
		return id;
	}

	/**
	 * 其次从数据库里面取
	 * 
	 * @return
	 */
	private synchronized Long getKeyTable(String tableName) {
		// 再次从缓存里面取下，这么做是为了防止高并发
		Long id = getKeyCache(tableName);
		if (id != null) {
			return id;
		}
		BaseKey bk = selectOne(tableName);
		// 没有，需要创建
		if (bk == null) {
			bk = new BaseKey();
			bk.setId(tableName);
			bk.setNowid("100");
			insert(bk);
			id = 1l;
		} else {
			id = NumberUtils.parseLong(bk.getNowid(), 100l);
			bk = new BaseKey();
			bk.setId(tableName);
			bk.setNowid((id + 100) + "");
			update(bk);
		}
		Long nextId = id + 1;
		keyMap.put(tableName, nextId);
		return id;
	}

}
