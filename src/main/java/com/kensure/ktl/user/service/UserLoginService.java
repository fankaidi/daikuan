/*
 * 文件名称: UserLoginServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-5
 * 修改内容: 
 */
package com.kensure.ktl.user.service;

import com.kensure.ktl.user.dao.UserLoginDao;
import com.kensure.ktl.user.model.UserLogin;
import com.kensure.ktl.user.service.UserLoginService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.frame.JSBaseService;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.MapUtils;


/**
 * 用户登录信息服务实现类
 * @author fankd created on 2017-9-5
 * @since 
 */
@Service
public class UserLoginService extends JSBaseService{
	
	@Resource
	private UserLoginDao dao;
    
    
    public UserLogin selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<UserLogin> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<UserLogin> selectAll(){
		return dao.selectAll();
	}
	
	public List<UserLogin> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(UserLogin obj){
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<UserLogin> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(UserLogin obj){
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
    
    
    public UserLogin selectBySessionId(String sessionId){
    	Map<String, Object> parameters = MapUtils.genMap("sessionid",sessionId.trim());
    	List<UserLogin> list = dao.selectByWhere(parameters);
    	UserLogin u = null;
    	if(CollectionUtils.isNotEmpty(list)){
    		u = list.get(0);
    	}
		return u;
	}
    
  

}
