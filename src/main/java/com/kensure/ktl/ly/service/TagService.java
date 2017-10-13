/*
 * 文件名称: TagServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-29
 * 修改内容: 
 */
package com.kensure.ktl.ly.service;

import com.kensure.ktl.ly.dao.TagDao;
import com.kensure.ktl.ly.model.Tag;
import com.kensure.ktl.ly.service.TagService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import co.kensure.frame.JSBaseService;


/**
 * 标签服务实现类
 * @author fankd created on 2017-9-29
 * @since 
 */
@Service
public class TagService extends JSBaseService{
	
	@Resource
	private TagDao dao;
    
    
    public Tag selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<Tag> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<Tag> selectAll(){
		return dao.selectAll();
	}
	
	public List<Tag> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(Tag obj){
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<Tag> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(Tag obj){
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
