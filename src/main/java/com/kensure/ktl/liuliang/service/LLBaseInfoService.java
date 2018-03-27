/*
 * 文件名称: LLBaseInfoServiceImpl.java
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.kensure.frame.JSBaseService;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.DateUtils;
import co.kensure.mem.MapUtils;
import co.kensure.mem.StringKSUtils;

import com.kensure.ktl.base.service.BaseKeyService;
import com.kensure.ktl.liuliang.dao.LLBaseInfoDao;
import com.kensure.ktl.liuliang.model.LLBaseInfo;


/**
 * 流量基础信息表服务实现类
 * @author fankd created on 2018-3-9
 * @since 
 */
@Service
public class LLBaseInfoService extends JSBaseService{
	
	@Resource
	private LLBaseInfoDao dao;
    
	@Resource
	private BaseKeyService baseKeyService;
    
    public LLBaseInfo selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<LLBaseInfo> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<LLBaseInfo> selectAll(){
		return dao.selectAll();
	}
	
	public List<LLBaseInfo> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(LLBaseInfo obj){
		obj.setId(baseKeyService.getKey("ll_base_info"));
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<LLBaseInfo> objs){
		for(LLBaseInfo obj:objs){
			obj.setId(baseKeyService.getKey("ll_base_info"));
			if(obj.getSourceId() == null){
				obj.setSourceId(obj.getId());
			}
		}	
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(LLBaseInfo obj){
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
     * 查找最新流量，并且对qq、微信、手机联系方式进行加密
     * @param parameters
     * @return
     */
    public List<LLBaseInfo> selectNewInfo(Map<String, Object> parameters){
    	parameters.put("orderby", "ll_base_info.create_date desc");
    	List<LLBaseInfo> list = dao.selectNewInfo(parameters);
    	for(LLBaseInfo base:list){
    		base.setQq(StringKSUtils.doMed(base.getQq()));
    		base.setWeixin(StringKSUtils.doMed(base.getWeixin()));
    		base.setMobile(StringKSUtils.doMed(base.getMobile()));
    	}
		return list;
	}
    
    /**
     * 统计最新流量
     * @param parameters
     * @return
     */
    public long selectNewInfoCount(Map<String, Object> parameters){
		return dao.selectNewInfoCount(parameters);
	}
    
    /**
     * 让流量进行第一次过期。
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void expireFirstBaseInfo(){
    	 Date dayago = DateUtils.getPastDay(new Date(), -4);
    	 Map<String, Object> parameters = MapUtils.genMap("expireCountIsNull",1,"status",1,"maxCreateDate",dayago);
    	 List<LLBaseInfo> list = selectByWhere(parameters);
    	 if(CollectionUtils.isEmpty(list)){
    		 return;
    	 }
    	 for(LLBaseInfo info:list){
    		 info.setExpireCount(1);
    		 info.setStatus(-1);
    		 update(info);
    	 }
	}
    
    /**
     * 让流量进行第二次过期。
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void expireSecondBaseInfo(){
    	 Date dayago = DateUtils.getPastDay(new Date(), -4);
    	 Map<String, Object> parameters = MapUtils.genMap("expireCount",1,"status",1,"maxUpdateDate",dayago);
    	 List<LLBaseInfo> list = selectByWhere(parameters);
    	 if(CollectionUtils.isEmpty(list)){
    		 return;
    	 }
    	 for(LLBaseInfo info:list){
    		 info.setExpireCount(2);
    		 info.setStatus(-1);
    		 update(info);
    	 }
	}
    
    /**
     * 让流量进行第一次恢复。
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void activeFirstBaseInfo(){
    	 Date dayago = DateUtils.getPastDay(new Date(), -3);
    	 Map<String, Object> parameters = MapUtils.genMap("expireCount",1,"status",-1,"maxUpdateDate",dayago);
    	 List<LLBaseInfo> list = selectByWhere(parameters);
    	 if(CollectionUtils.isEmpty(list)){
    		 return;
    	 }
    	 for(LLBaseInfo info:list){
    		 info.setStatus(1);
    		 update(info);
    	 }
	}
  

}
