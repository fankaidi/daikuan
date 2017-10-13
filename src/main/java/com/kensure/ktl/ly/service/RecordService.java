/*
 * 文件名称: RecordServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-29
 * 修改内容: 
 */
package com.kensure.ktl.ly.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.frame.JSBaseService;

import com.kensure.ktl.ly.dao.RecordDao;
import com.kensure.ktl.ly.model.Record;

/**
 * 发布记录服务实现类
 * 
 * @author fankd created on 2017-9-29
 * @since
 */
@Service
public class RecordService extends JSBaseService {

	@Resource
	private RecordDao dao;

	public Record selectOne(Long id) {
		return dao.selectOne(id);
	}

	public List<Record> selectByIds(Collection<Long> ids) {
		return dao.selectByIds(ids);
	}

	public List<Record> selectAll() {
		return dao.selectAll();
	}

	public List<Record> selectByWhere(Map<String, Object> parameters) {
		return dao.selectByWhere(parameters);
	}

	public long selectCount() {
		return dao.selectCount();
	}

	public long selectCountByWhere(Map<String, Object> parameters) {
		return dao.selectCountByWhere(parameters);
	}

	public boolean insert(Record obj) {
		obj.setId(System.currentTimeMillis());
		Date date = new Date();
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		obj.setPublishDate(date);
		return dao.insert(obj);
	}

	public boolean insertInBatch(List<Record> objs) {
		return dao.insertInBatch(objs);
	}

	public boolean update(Record obj) {
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

}
