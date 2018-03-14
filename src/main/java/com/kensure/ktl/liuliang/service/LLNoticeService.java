/*
 * 文件名称: LLNoticeServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-3-13
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
import com.kensure.ktl.liuliang.dao.LLNoticeDao;
import com.kensure.ktl.liuliang.model.LLNotice;


/**
 * 最新通知服务实现类
 * @author fankd created on 2018-3-13
 * @since 
 */
@Service
public class LLNoticeService extends JSBaseService{
	
	@Resource
	private LLNoticeDao dao;
	
	@Resource
	private BaseKeyService baseKeyService;
    
    
    public LLNotice selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<LLNotice> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<LLNotice> selectAll(){
		return dao.selectAll();
	}
	
	public List<LLNotice> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(LLNotice obj){
		obj.setId(baseKeyService.getKey("ll_notice"));
		Date date = new Date();
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		obj.setStatus(1);
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<LLNotice> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(LLNotice obj){
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
