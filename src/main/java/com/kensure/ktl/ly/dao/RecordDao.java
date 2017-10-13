/*
 * 文件名称: RecordDao.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-29
 * 修改内容: 
 */
package com.kensure.ktl.ly.dao;
import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import com.kensure.ktl.ly.model.Record;

/**
 * 发布记录Dao接口类
 * 
 * @author fankd created on 2017-9-29
 * @since 
 */
 @MyBatisRepository
public interface RecordDao extends JSBaseDao<Record> {
	
	
	public Record selectOne(Long id);
	
	public List<Record> selectByIds(Collection<Long> ids);
	
	public List<Record> selectAll();
	
	public List<Record> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(Record obj);
	
	public boolean insertInBatch(List<Record> objs);
	
	
	public boolean update(Record obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
