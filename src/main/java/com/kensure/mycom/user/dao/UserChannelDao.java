/*
 * 文件名称: UserChannelDao.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-5-8
 * 修改内容: 
 */
package com.kensure.mycom.user.dao;
import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import com.kensure.mycom.user.model.UserChannel;

/**
 * 用户渠道信息Dao接口类
 * 
 * @author fankd created on 2018-5-8
 * @since 
 */
 @MyBatisRepository
public interface UserChannelDao extends JSBaseDao<UserChannel> {
	
	
	public UserChannel selectOne(Long id);
	
	public List<UserChannel> selectByIds(Collection<Long> ids);
	
	public List<UserChannel> selectAll();
	
	public List<UserChannel> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(UserChannel obj);
	
	public boolean insertInBatch(List<UserChannel> objs);
	
	
	public boolean update(UserChannel obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
