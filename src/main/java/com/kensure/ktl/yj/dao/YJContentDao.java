/*
 * 文件名称: YJContentDao.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-4-19
 * 修改内容: 
 */
package com.kensure.ktl.yj.dao;
import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import com.kensure.ktl.yj.model.YJContent;

/**
 * 游记内容表Dao接口类
 * 
 * @author fankd created on 2018-4-19
 * @since 
 */
 @MyBatisRepository
public interface YJContentDao extends JSBaseDao<YJContent> {
	
	
	public YJContent selectOne(Long id);
	
	public List<YJContent> selectByIds(Collection<Long> ids);
	
	public List<YJContent> selectAll();
	
	public List<YJContent> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(YJContent obj);
	
	public boolean insertInBatch(List<YJContent> objs);
	
	
	public boolean update(YJContent obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
