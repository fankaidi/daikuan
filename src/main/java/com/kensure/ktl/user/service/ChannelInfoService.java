/*
 * 文件名称: ChannelInfoServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-10-12
 * 修改内容: 
 */
package com.kensure.ktl.user.service;

import com.kensure.ktl.user.dao.ChannelInfoDao;
import com.kensure.ktl.user.model.ChannelInfo;
import com.kensure.ktl.user.service.ChannelInfoService;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.frame.JSBaseService;
import co.kensure.mem.NumberUtils;
import co.kensure.mem.Utils;


/**
 * 渠道信息服务实现类
 * @author fankd created on 2017-10-12
 * @since 
 */
@Service
public class ChannelInfoService extends JSBaseService{
	
	@Resource
	private ChannelInfoDao dao;
    
    
    public ChannelInfo selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<ChannelInfo> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<ChannelInfo> selectAll(){
		return dao.selectAll();
	}
	
	public List<ChannelInfo> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(ChannelInfo obj){
		String id = System.currentTimeMillis()+Utils.randomSMSCode();
		obj.setId(NumberUtils.parseLong(id, null));
		Date date = new Date();
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		obj.setStatus(0);
		if(obj.getCid() == null){
			obj.setCid(0);
		}
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<ChannelInfo> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(ChannelInfo obj){
		Date date = new Date();
		obj.setUpdateDate(date);
		return dao.update(obj);
	}
	
	public boolean updateSuccess(Long uuid,String mobile){
		ChannelInfo one = selectOne(uuid);
		one.setMobile(mobile);	
		one.setStatus(1);
		return update(one);
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
    
    
    public List<Map> groupByCid(Map<String, Object> parameters){
		return dao.groupByCid(parameters);
	}
    
  

}
