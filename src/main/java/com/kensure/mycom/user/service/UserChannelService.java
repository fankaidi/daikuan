/*
 * 文件名称: UserChannelServiceImpl.java
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

import com.kensure.mycom.base.service.BaseKeyService;
import com.kensure.mycom.user.dao.UserChannelDao;
import com.kensure.mycom.user.model.UserChannel;


/**
 * 用户渠道信息服务实现类
 * @author fankd created on 2018-5-8
 * @since 
 */
@Service
public class UserChannelService extends JSBaseService{
	
	@Resource
	private UserChannelDao dao;
    
	@Resource
	private BaseKeyService baseKeyService;
    
    public UserChannel selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<UserChannel> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<UserChannel> selectAll(){
		return dao.selectAll();
	}
	
	public List<UserChannel> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(UserChannel obj){
		Long id = baseKeyService.getKey("w_user_channel");
		Date date = new Date();
		obj.setId(id);
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<UserChannel> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(UserChannel obj){
		Date date = new Date();
		obj.setUpdateDate(date);
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
    
    /**
     * 根据开放id和渠道获取 用户信息
     * @param openId
     * @param channelId
     * @return
     */
    public UserChannel selectByOpenId(String openId,Integer channelId){
    	Map<String, Object> parameters = MapUtils.genMap("openid",openId.trim(),"channelid",channelId);
    	List<UserChannel> list = dao.selectByWhere(parameters);
    	UserChannel u = null;
    	if(CollectionUtils.isNotEmpty(list)){
    		u = list.get(0);
    	}
		return u;
	}
  
}
