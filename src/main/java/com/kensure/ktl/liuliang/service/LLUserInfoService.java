/*
 * 文件名称: LLUserInfoServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-3-8
 * 修改内容: 
 */
package com.kensure.ktl.liuliang.service;

import com.kensure.ktl.base.service.BaseKeyService;
import com.kensure.ktl.liuliang.dao.LLUserInfoDao;
import com.kensure.ktl.liuliang.model.LLUserInfo;
import com.kensure.ktl.liuliang.model.LLUserLogin;
import com.kensure.ktl.liuliang.service.LLUserInfoService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.frame.JSBaseService;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.MapUtils;

/**
 * 流量商户表服务实现类
 * 
 * @author fankd created on 2018-3-8
 * @since
 */
@Service
public class LLUserInfoService extends JSBaseService {

	@Resource
	private BaseKeyService baseKeyService;
	
	@Resource
	private LLUserInfoDao dao;

	@Resource
	private LLUserLoginService llUserLoginService;

	public LLUserInfo selectOne(Long id) {
		return dao.selectOne(id);
	}

	public List<LLUserInfo> selectByIds(Collection<Long> ids) {
		return dao.selectByIds(ids);
	}

	public List<LLUserInfo> selectAll() {
		return dao.selectAll();
	}

	public List<LLUserInfo> selectByWhere(Map<String, Object> parameters) {
		return dao.selectByWhere(parameters);
	}

	public long selectCount() {
		return dao.selectCount();
	}

	public long selectCountByWhere(Map<String, Object> parameters) {
		return dao.selectCountByWhere(parameters);
	}

	public boolean insert(LLUserInfo obj) {
		Date date = new Date();
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		obj.setStatus(1);
		obj.setId(baseKeyService.getKey("ll_user_info"));
		return dao.insert(obj);
	}

	public boolean insertInBatch(List<LLUserInfo> objs) {
		return dao.insertInBatch(objs);
	}

	public boolean update(LLUserInfo obj) {
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

	public LLUserInfo selectBySessionId(String sessionId) {
		LLUserLogin login = llUserLoginService.selectBySessionId(sessionId);
		LLUserInfo u = dao.selectOne(login.getUserid());
		if (u == null) {
			BusinessExceptionUtil.threwException("找不到该用户");
		}

		if (u.getStatus() != 1) {
			BusinessExceptionUtil.threwException("该用户已停用");
		}
		return u;
	}
	
	public LLUserInfo selectByLoginName(String loginname) {		
		Map<String, Object> parameters = MapUtils.genMap("loginname", loginname);
		List<LLUserInfo> list = selectByWhere(parameters);
		LLUserInfo u = null;
		if (CollectionUtils.isNotEmpty(list)) {
			u = list.get(0);
		}
		return u;
	}
}
