/*
 * 文件名称: UserInfoDao.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-6
 * 修改内容: 
 */
package com.kensure.ktl.user.dao;
import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import com.kensure.ktl.user.model.UserInfo;

/**
 * 用户信息表Dao接口类
 * 
 * @author fankd created on 2017-9-6
 * @since 
 */
 @MyBatisRepository
public interface UserInfoDao extends JSBaseDao<UserInfo> {
	
	
	public UserInfo selectOne(Long id);
	
	public List<UserInfo> selectByIds(Collection<Long> ids);
	
	public List<UserInfo> selectAll();
	
	public List<UserInfo> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(UserInfo obj);
	
	public boolean insertInBatch(List<UserInfo> objs);
	
	
	public boolean update(UserInfo obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
