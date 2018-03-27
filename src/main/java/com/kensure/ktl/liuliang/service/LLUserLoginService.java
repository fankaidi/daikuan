/*
 * 文件名称: LLUserLoginServiceImpl.java
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

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.frame.JSBaseService;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.MapUtils;
import co.kensure.mem.Utils;

import com.kensure.ktl.base.service.BaseKeyService;
import com.kensure.ktl.liuliang.dao.LLUserLoginDao;
import com.kensure.ktl.liuliang.model.LLUserInfo;
import com.kensure.ktl.liuliang.model.LLUserLogin;

/**
 * 流量商户登录表服务实现类
 * 
 * @author fankd created on 2018-3-8
 * @since
 */
@Service
public class LLUserLoginService extends JSBaseService {

	@Resource
	private LLUserLoginDao dao;

	@Resource
	private LLUserInfoService llUserInfoService;

	@Resource
	private BaseKeyService baseKeyService;

	public LLUserLogin selectOne(Long id) {
		return dao.selectOne(id);
	}

	public List<LLUserLogin> selectByIds(Collection<Long> ids) {
		return dao.selectByIds(ids);
	}

	public List<LLUserLogin> selectAll() {
		return dao.selectAll();
	}

	public List<LLUserLogin> selectByWhere(Map<String, Object> parameters) {
		return dao.selectByWhere(parameters);
	}

	public long selectCount() {
		return dao.selectCount();
	}

	public long selectCountByWhere(Map<String, Object> parameters) {
		return dao.selectCountByWhere(parameters);
	}

	public boolean insert(LLUserLogin obj) {
		Date date = new Date();
		obj.setId(baseKeyService.getKey("ll_user_login"));
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		obj.setStatus(1);
		obj.setSessionid(obj.getId() + "" + Utils.randomSMSCode());
		if (StringUtils.length(obj.getUa()) > 250) {
			obj.setUa(obj.getUa().substring(0, 250));
		}
		return dao.insert(obj);
	}

	public boolean insertInBatch(List<LLUserLogin> objs) {
		return dao.insertInBatch(objs);
	}

	public boolean update(LLUserLogin obj) {
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

	public LLUserLogin selectBySessionId(String sessionId) {
		Map<String, Object> parameters = MapUtils.genMap("sessionid", sessionId.trim());
		List<LLUserLogin> list = dao.selectByWhere(parameters);
		LLUserLogin u = null;
		if (CollectionUtils.isNotEmpty(list)) {
			u = list.get(0);
		}
		if (u == null) {
			BusinessExceptionUtil.threwException("无效的会话");
		}

		if (u.getStatus() != 1) {
			BusinessExceptionUtil.threwException("过期的会话");
		}
		return u;
	}

	/**
	 * 用户退出
	 * 
	 * @param sessionId
	 * @return
	 */
	public void loginOut(String sessionId) {
		LLUserLogin u = selectBySessionId(sessionId);
		u.setStatus(-1);
		u.setOuttimeDate(new Date());
		update(u);
	}

	/**
	 * 用户登录逻辑
	 * 
	 * @param loginname
	 * @param password
	 * @return
	 */
	public LLUserLogin login(String loginname, String password, co.kensure.thread.Session se) {
		Map<String, Object> map = MapUtils.genMap("loginname", loginname);
		List<LLUserInfo> list = llUserInfoService.selectByWhere(map);
		if (CollectionUtils.isEmpty(list)) {
			BusinessExceptionUtil.threwException("用户不存在，请注册");
		}
		LLUserInfo user = list.get(0);
		if (!user.getPwd().equalsIgnoreCase(password)) {
			BusinessExceptionUtil.threwException("密码错误!");
		}
		LLUserLogin userLogin = new LLUserLogin();
		userLogin.setUserid(user.getId());
		userLogin.setIp(se.getIp());
		userLogin.setUa(se.getUserAgent());
		insert(userLogin);
		return userLogin;
	}

}
