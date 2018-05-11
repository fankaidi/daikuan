/*
 * 文件名称: UserServiceImpl.java
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

import com.kensure.mycom.base.service.BaseKeyService;
import com.kensure.mycom.user.dao.UserDao;
import com.kensure.mycom.user.model.User;
import com.kensure.mycom.user.service.UserService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.frame.JSBaseService;


/**
 * 用户主要信息服务实现类
 * @author fankd created on 2018-5-8
 * @since 
 */
@Service
public class UserService extends JSBaseService{
	
	@Resource
	private UserDao dao;
    
	@Resource
	private BaseKeyService baseKeyService;
	
    public User selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<User> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<User> selectAll(){
		return dao.selectAll();
	}
	
	public List<User> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(User obj){
		Date date = new Date();
		obj.setId(baseKeyService.getKey("w_user"));
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		obj.setStatus(1);
		obj.setPwd("aaaaaa");
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<User> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(User obj){
		return dao.update(obj);
	}
    
    public boolean updateByMap(Map<String, Object> params){
		return dao.updateByMap(params);
	}
    
    
	public boolean delete(Long id){
		return dao.delete(id);
	}	
	
    public boolean deleteMulti(Collection<Long> ids){
		return dao.deleteMulti(ids);
	}
    
    public boolean deleteByWhere(Map<String, Object> parameters){
		return dao.deleteByWhere(parameters);
	}
    
    
  

}
