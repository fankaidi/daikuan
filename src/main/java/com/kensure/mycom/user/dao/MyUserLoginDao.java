/*
 * 文件名称: MyUserLoginDao.java
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
import com.kensure.mycom.user.model.MyUserLogin;

/**
 * 用户登录Dao接口类
 * 
 * @author fankd created on 2018-5-8
 * @since 
 */
 @MyBatisRepository
public interface MyUserLoginDao extends JSBaseDao<MyUserLogin> {
	
	
	public MyUserLogin selectOne(Long id);
	
	public List<MyUserLogin> selectByIds(Collection<Long> ids);
	
	public List<MyUserLogin> selectAll();
	
	public List<MyUserLogin> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(MyUserLogin obj);
	
	public boolean insertInBatch(List<MyUserLogin> objs);
	
	
	public boolean update(MyUserLogin obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
