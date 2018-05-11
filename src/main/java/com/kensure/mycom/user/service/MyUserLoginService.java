/*
 * 文件名称: MyUserLoginServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-5-8
 * 修改内容: 
 */
package com.kensure.mycom.user.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.frame.JSBaseService;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.MapUtils;
import co.kensure.mem.Utils;

import com.kensure.mycom.base.service.BaseKeyService;
import com.kensure.mycom.user.dao.MyUserLoginDao;
import com.kensure.mycom.user.model.MyUserLogin;
import com.kensure.mycom.user.model.User;
import com.kensure.mycom.user.model.UserChannel;
import com.kensure.mycom.user.model.UserSession;

/**
 * 用户登录服务实现类
 * 
 * @author fankd created on 2018-5-8
 * @since
 */
@Service
public class MyUserLoginService extends JSBaseService {

	@Resource
	private UserService userService;

	@Resource
	private UserChannelService userChannelService;

	@Resource
	private MyUserLoginDao dao;

	@Resource
	private BaseKeyService baseKeyService;

	public MyUserLogin selectOne(Long id) {
		return dao.selectOne(id);
	}

	public List<MyUserLogin> selectByIds(Collection<Long> ids) {
		return dao.selectByIds(ids);
	}

	public List<MyUserLogin> selectAll() {
		return dao.selectAll();
	}

	public List<MyUserLogin> selectByWhere(Map<String, Object> parameters) {
		return dao.selectByWhere(parameters);
	}

	public long selectCount() {
		return dao.selectCount();
	}

	public long selectCountByWhere(Map<String, Object> parameters) {
		return dao.selectCountByWhere(parameters);
	}

	public boolean insert(MyUserLogin obj) {
		Date date = new Date();
		obj.setId(baseKeyService.getKey("w_user_login"));
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		obj.setStatus(1);
		obj.setSessionid(Utils.getUUID());
		return dao.insert(obj);
	}

	public boolean insertInBatch(List<MyUserLogin> objs) {
		return dao.insertInBatch(objs);
	}

	public boolean update(MyUserLogin obj) {
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
	 * 获取用户登录信息
	 * 
	 * @param sessionId
	 * @return
	 */
	public MyUserLogin selectBySessionId(String sessionId) {
		Map<String, Object> parameters = MapUtils.genMap("sessionid", sessionId.trim());
		List<MyUserLogin> list = dao.selectByWhere(parameters);
		MyUserLogin u = null;
		if (CollectionUtils.isNotEmpty(list)) {
			u = list.get(0);
		}
		return u;
	}

	/**
	 * 渠道用户登录逻辑
	 */
	public MyUserLogin loginChannel(UserChannel uc) {
		MyUserLogin userLogin = new MyUserLogin();
		userLogin.setUserid(uc.getUserid());
		userLogin.setChannelid(uc.getId());
		insert(userLogin);
		return userLogin;
	}

	/**
	 * 根据sessionId获取用户信息
	 */
	public UserSession getUserSessionBySessionId(String sessionId) {
		MyUserLogin userlogin = selectBySessionId(sessionId);
		UserSession us = new UserSession();
		if (userlogin.getChannelid() != null) {
			UserChannel ucs = userChannelService.selectOne(userlogin.getChannelid());
			us.setName(ucs.getName());
			us.setOpenId(ucs.getOpenid());
		}
		if (userlogin.getUserid() != null) {
			User user = userService.selectOne(userlogin.getUserid());
			us.setRealname(user.getName());
			us.setNickname(user.getNickname());
		}
		us.setSessionid(sessionId);
		return us;
	}

}
