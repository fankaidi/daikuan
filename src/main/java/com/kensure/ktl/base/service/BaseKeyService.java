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
package com.kensure.ktl.base.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.frame.JSBaseService;
import co.kensure.mem.NumberUtils;

import com.kensure.ktl.base.dao.BaseKeyDao;
import com.kensure.ktl.base.model.BaseKey;

/**
 * 主键表服务实现类
 * 
 * @author fankd created on 2018-3-13
 * @since
 */
@Service
public class BaseKeyService extends JSBaseService {

	@Resource
	private BaseKeyDao dao;

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
		Date date = new Date();
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		return dao.insert(obj);
	}

	public boolean insertInBatch(List<BaseKey> objs) {
		return dao.insertInBatch(objs);
	}

	public boolean update(BaseKey obj) {
		Date date = new Date();
		obj.setUpdateDate(date);
		return dao.update(obj);
	}

	public boolean updateByMap(Map<String, Object> params) {
		return dao.updateByMap(params);
	}

	public boolean delete(Long id) {
		return dao.delete(id);
	}

	public boolean deleteMulti(Collection<Long> ids) {
		return dao.deleteMulti(ids);
	}

	public boolean deleteByWhere(Map<String, Object> parameters) {
		return dao.deleteByWhere(parameters);
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
		Long id = null;
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
