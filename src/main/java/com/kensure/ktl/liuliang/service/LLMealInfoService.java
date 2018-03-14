/*
 * 文件名称: LLMealInfoServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-3-9
 * 修改内容: 
 */
package com.kensure.ktl.liuliang.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.frame.JSBaseService;

import com.kensure.ktl.base.service.BaseKeyService;
import com.kensure.ktl.liuliang.dao.LLMealInfoDao;
import com.kensure.ktl.liuliang.model.LLMealInfo;


/**
 * 套餐信息服务实现类
 * @author fankd created on 2018-3-9
 * @since 
 */
@Service
public class LLMealInfoService extends JSBaseService{
	
	@Resource
	private LLMealInfoDao dao;
    
	@Resource
	private BaseKeyService baseKeyService;
    
    public LLMealInfo selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<LLMealInfo> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<LLMealInfo> selectAll(){
		return dao.selectAll();
	}
	
	public List<LLMealInfo> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(LLMealInfo obj){
		obj.setId(baseKeyService.getKey("ll_meal_info"));
		Date date = new Date();
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<LLMealInfo> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(LLMealInfo obj){
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
