/*
 * 文件名称: YJTitleDao.java
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
import com.kensure.ktl.yj.model.YJTitle;

/**
 * 游记主表Dao接口类
 * 
 * @author fankd created on 2018-4-19
 * @since 
 */
 @MyBatisRepository
public interface YJTitleDao extends JSBaseDao<YJTitle> {
	
	
	public YJTitle selectOne(Long id);
	
	public List<YJTitle> selectByIds(Collection<Long> ids);
	
	public List<YJTitle> selectAll();
	
	public List<YJTitle> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(YJTitle obj);
	
	public boolean insertInBatch(List<YJTitle> objs);
	
	
	public boolean update(YJTitle obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
