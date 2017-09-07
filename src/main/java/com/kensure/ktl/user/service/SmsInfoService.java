/*
 * 文件名称: SmsInfoServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-6
 * 修改内容: 
 */
package com.kensure.ktl.user.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.frame.JSBaseService;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.MapUtils;

import com.kensure.ktl.user.dao.SmsInfoDao;
import com.kensure.ktl.user.model.SmsInfo;


/**
 * 验证码服务实现类
 * @author fankd created on 2017-9-6
 * @since 
 */
@Service
public class SmsInfoService extends JSBaseService{
	
	@Resource
	private SmsInfoDao dao;
    
    
    public SmsInfo selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<SmsInfo> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<SmsInfo> selectAll(){
		return dao.selectAll();
	}
	
	public List<SmsInfo> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(SmsInfo obj){
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<SmsInfo> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(SmsInfo obj){
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
    
    
    public SmsInfo selectByMobile(String mobile){
    	Map<String, Object> parameters = MapUtils.genMap("mobile",mobile);
    	List<SmsInfo> userlist = selectByWhere(parameters);
    	SmsInfo u = null;
    	if(CollectionUtils.isNotEmpty(userlist)){
    		u = userlist.get(0);
    	}	
		return u;
	}
    
    
  

}
