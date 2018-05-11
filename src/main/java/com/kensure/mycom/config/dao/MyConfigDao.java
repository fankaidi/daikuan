/*
 * 文件名称: MyConfigDao.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-5-8
 * 修改内容: 
 */
package com.kensure.mycom.config.dao;
import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import com.kensure.mycom.config.model.MyConfig;

/**
 * 配置信息Dao接口类
 * 
 * @author fankd created on 2018-5-8
 * @since 
 */
 @MyBatisRepository
public interface MyConfigDao extends JSBaseDao<MyConfig> {
	
	
	public MyConfig selectOne(Long id);
	
	public List<MyConfig> selectByIds(Collection<Long> ids);
	
	public List<MyConfig> selectAll();
	
	public List<MyConfig> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(MyConfig obj);
	
	public boolean insertInBatch(List<MyConfig> objs);
	
	
	public boolean update(MyConfig obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
