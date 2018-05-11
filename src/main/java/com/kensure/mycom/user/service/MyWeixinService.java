/*
 * 文件名称: MyWeixinServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-5-10
 * 修改内容: 
 */
package com.kensure.mycom.user.service;

import com.kensure.mycom.user.dao.MyWeixinDao;
import com.kensure.mycom.user.model.MyWeixin;
import com.kensure.mycom.user.service.MyWeixinService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import co.kensure.frame.JSBaseService;


/**
 * 用户微信信息服务实现类
 * @author fankd created on 2018-5-10
 * @since 
 */
@Service
public class MyWeixinService extends JSBaseService{
	
	@Resource
	private MyWeixinDao dao;
    
    
    public MyWeixin selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MyWeixin> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MyWeixin> selectAll(){
		return dao.selectAll();
	}
	
	public List<MyWeixin> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MyWeixin obj){
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<MyWeixin> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(MyWeixin obj){
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
