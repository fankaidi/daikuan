/*
 * 文件名称: TagTypeDao.java
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
import com.kensure.ktl.ly.model.TagType;

/**
 * 标签类型Dao接口类
 * 
 * @author fankd created on 2017-9-29
 * @since 
 */
 @MyBatisRepository
public interface TagTypeDao extends JSBaseDao<TagType> {
	
	
	public TagType selectOne(Long id);
	
	public List<TagType> selectByIds(Collection<Long> ids);
	
	public List<TagType> selectAll();
	
	public List<TagType> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(TagType obj);
	
	public boolean insertInBatch(List<TagType> objs);
	
	
	public boolean update(TagType obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
