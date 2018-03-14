/*
 * 文件名称: LLUserLoginDao.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-3-9
 * 修改内容: 
 */
package com.kensure.ktl.liuliang.dao;
import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import com.kensure.ktl.liuliang.model.LLUserLogin;

/**
 * 流量商户登录表Dao接口类
 * 
 * @author fankd created on 2018-3-9
 * @since 
 */
 @MyBatisRepository
public interface LLUserLoginDao extends JSBaseDao<LLUserLogin> {
	
	
	public LLUserLogin selectOne(Long id);
	
	public List<LLUserLogin> selectByIds(Collection<Long> ids);
	
	public List<LLUserLogin> selectAll();
	
	public List<LLUserLogin> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(LLUserLogin obj);
	
	public boolean insertInBatch(List<LLUserLogin> objs);
	
	
	public boolean update(LLUserLogin obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
