/*
 * 文件名称: MyConfigServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-5-8
 * 修改内容: 
 */
package com.kensure.mycom.config.service;

import com.kensure.mycom.config.dao.MyConfigDao;
import com.kensure.mycom.config.model.MyConfig;
import com.kensure.mycom.config.service.MyConfigService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.frame.JSBaseService;
import co.kensure.mem.CollectionUtils;


/**
 * 配置信息服务实现类
 * @author fankd created on 2018-5-8
 * @since 
 */
@Service
public class MyConfigService extends JSBaseService{

	private static Map<String,MyConfig> map = new HashMap<String,MyConfig>();
	
	@Resource
	private MyConfigDao dao;
	
    public MyConfig selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<MyConfig> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<MyConfig> selectAll(){
		return dao.selectAll();
	}
	
	public List<MyConfig> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(MyConfig obj){
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<MyConfig> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(MyConfig obj){
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
    
    public void initCache(){
    	map.clear();
    	List<MyConfig> list = selectAll();
    	if(CollectionUtils.isEmpty(list)){
    		return;
    	}
    	for(MyConfig my:list){
    		map.put(my.getId(), my);
    	}	
    }
    
    /**
     * key value 模式，获取配置信息
     * @param id
     * @return
     */
    public static MyConfig getMyConfig(String id){
    	MyConfig myConfig = map.get(id);
    	return myConfig;
    }
    
}
