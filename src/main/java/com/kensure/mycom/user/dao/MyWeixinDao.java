/*
 * 文件名称: MyWeixinDao.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-5-10
 * 修改内容: 
 */
package com.kensure.mycom.user.dao;
import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import com.kensure.mycom.user.model.MyWeixin;

/**
 * 用户微信信息Dao接口类
 * 
 * @author fankd created on 2018-5-10
 * @since 
 */
 @MyBatisRepository
public interface MyWeixinDao extends JSBaseDao<MyWeixin> {
	
	
	public MyWeixin selectOne(Long id);
	
	public List<MyWeixin> selectByIds(Collection<Long> ids);
	
	public List<MyWeixin> selectAll();
	
	public List<MyWeixin> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(MyWeixin obj);
	
	public boolean insertInBatch(List<MyWeixin> objs);
	
	
	public boolean update(MyWeixin obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
