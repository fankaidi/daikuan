/*
 * 文件名称: YJContentServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-4-19
 * 修改内容: 
 */
package com.kensure.ktl.yj.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.frame.JSBaseService;

import com.kensure.ktl.yj.dao.YJContentDao;
import com.kensure.ktl.yj.model.YJContent;
import com.kensure.mycom.base.service.BaseKeyService;


/**
 * 游记内容表服务实现类
 * @author fankd created on 2018-4-19
 * @since 
 */
@Service
public class YJContentService extends JSBaseService{
	
	@Resource
	private YJContentDao dao;
	
	@Resource
	private BaseKeyService baseKeyService;
     
    public YJContent selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<YJContent> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<YJContent> selectAll(){
		return dao.selectAll();
	}
	
	public List<YJContent> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(YJContent obj){
		Long id = baseKeyService.getKey("yj_content");
		obj.setId(id);
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<YJContent> objs){
		for(YJContent obj:objs){
			Long id = baseKeyService.getKey("yj_content");
			obj.setId(id);
		}
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(YJContent obj){
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
