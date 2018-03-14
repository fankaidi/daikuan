/*
 * 文件名称: LLUserInfoDao.java
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
import com.kensure.ktl.liuliang.model.LLUserInfo;

/**
 * 商户信息表Dao接口类
 * 
 * @author fankd created on 2018-3-9
 * @since 
 */
 @MyBatisRepository
public interface LLUserInfoDao extends JSBaseDao<LLUserInfo> {
	
	
	public LLUserInfo selectOne(Long id);
	
	public List<LLUserInfo> selectByIds(Collection<Long> ids);
	
	public List<LLUserInfo> selectAll();
	
	public List<LLUserInfo> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(LLUserInfo obj);
	
	public boolean insertInBatch(List<LLUserInfo> objs);
	
	
	public boolean update(LLUserInfo obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
