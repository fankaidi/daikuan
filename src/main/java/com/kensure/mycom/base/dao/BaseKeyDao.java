/*
 * 文件名称: BaseKeyDao.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-3-13
 * 修改内容: 
 */
package com.kensure.mycom.base.dao;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;

import com.kensure.mycom.base.model.BaseKey;

/**
 * 主键表Dao接口类
 * 
 * @author fankd created on 2018-3-13
 * @since 
 */
 @MyBatisRepository
public interface BaseKeyDao extends JSBaseDao<BaseKey> {
	
	
	public BaseKey selectOne(String id);
	
	public List<BaseKey> selectByIds(Collection<Long> ids);
	
	public List<BaseKey> selectAll();
	
	public List<BaseKey> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(BaseKey obj);
	
	public boolean insertInBatch(List<BaseKey> objs);
	
	
	public boolean update(BaseKey obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
