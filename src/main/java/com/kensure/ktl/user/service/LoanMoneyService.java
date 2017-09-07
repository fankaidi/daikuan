/*
 * 文件名称: LoanMoneyServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-9-5
 * 修改内容: 
 */
package com.kensure.ktl.user.service;

import com.kensure.ktl.user.dao.LoanMoneyDao;
import com.kensure.ktl.user.model.LoanMoney;
import com.kensure.ktl.user.service.LoanMoneyService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import co.kensure.frame.JSBaseService;


/**
 * 用户借款记录服务实现类
 * @author fankd created on 2017-9-5
 * @since 
 */
@Service
public class LoanMoneyService extends JSBaseService{
	
	@Resource
	private LoanMoneyDao dao;
    
    
    public LoanMoney selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<LoanMoney> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<LoanMoney> selectAll(){
		return dao.selectAll();
	}
	
	public List<LoanMoney> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(LoanMoney obj){
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<LoanMoney> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(LoanMoney obj){
		return dao.update(obj);
	}
    
    public boolean updateByMap(Map<String, Object> params){
		return dao.updateByMap(params);
	}
    
    
	public boolean delete(Long id){
		return dao.delete(id);
	}	
	
    public boolean deleteMulti(Collection<Long> ids){
		return dao.deleteMulti(ids);
	}
    
    public boolean deleteByWhere(Map<String, Object> parameters){
		return dao.deleteByWhere(parameters);
	}
    
    
  

}
