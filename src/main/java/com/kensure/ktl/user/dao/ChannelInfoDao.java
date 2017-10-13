/*
 * 文件名称: ChannelInfoDao.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-10-12
 * 修改内容: 
 */
package com.kensure.ktl.user.dao;
import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import com.kensure.ktl.user.model.ChannelInfo;

/**
 * 渠道信息Dao接口类
 * 
 * @author fankd created on 2017-10-12
 * @since 
 */
 @MyBatisRepository
public interface ChannelInfoDao extends JSBaseDao<ChannelInfo> {
	
	
	public ChannelInfo selectOne(Long id);
	
	public List<ChannelInfo> selectByIds(Collection<Long> ids);
	
	public List<ChannelInfo> selectAll();
	
	public List<ChannelInfo> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(ChannelInfo obj);
	
	public boolean insertInBatch(List<ChannelInfo> objs);
	
	
	public boolean update(ChannelInfo obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
    
    public List<Map> groupByCid(Map<String, Object> parameters);
	
}
