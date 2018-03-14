/*
 * 文件名称: LLNoticeDao.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-3-13
 * 修改内容: 
 */
package com.kensure.ktl.liuliang.dao;
import co.kensure.annotation.MyBatisRepository;
import co.kensure.frame.JSBaseDao;
import java.util.List;
import java.util.Collection;
import java.util.Map;
import com.kensure.ktl.liuliang.model.LLNotice;

/**
 * 最新通知Dao接口类
 * 
 * @author fankd created on 2018-3-13
 * @since 
 */
 @MyBatisRepository
public interface LLNoticeDao extends JSBaseDao<LLNotice> {
	
	
	public LLNotice selectOne(Long id);
	
	public List<LLNotice> selectByIds(Collection<Long> ids);
	
	public List<LLNotice> selectAll();
	
	public List<LLNotice> selectByWhere(Map<String, Object> parameters);
	
	
	public long selectCount();
	
	public long selectCountByWhere(Map<String, Object> parameters);
	
	
	public boolean insert(LLNotice obj);
	
	public boolean insertInBatch(List<LLNotice> objs);
	
	
	public boolean update(LLNotice obj);
    
    public boolean updateByMap(Map<String, Object> params);
    
    
	public boolean delete(Long id);	
	
    public boolean deleteMulti(Collection<Long> ids);
    
    public boolean deleteByWhere(Map<String, Object> parameters);
	
	
}
