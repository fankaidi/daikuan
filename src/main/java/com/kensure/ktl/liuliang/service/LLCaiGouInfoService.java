/*
 * 文件名称: LLCaiGouInfoServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-3-9
 * 修改内容: 
 */
package com.kensure.ktl.liuliang.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.frame.JSBaseService;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.MapUtils;
import co.kensure.mem.NumberUtils;

import com.kensure.ktl.base.service.BaseKeyService;
import com.kensure.ktl.liuliang.dao.LLCaiGouInfoDao;
import com.kensure.ktl.liuliang.model.LLBaseInfo;
import com.kensure.ktl.liuliang.model.LLCaiGouInfo;
import com.kensure.ktl.liuliang.model.LLMealSale;
import com.kensure.ktl.liuliang.model.LLUserInfo;


/**
 * 采购流量详细信息服务实现类
 * @author fankd created on 2018-3-9
 * @since 
 */
@Service
public class LLCaiGouInfoService extends JSBaseService{
	
	@Resource
	private LLCaiGouInfoDao dao;
	@Resource
	private LLUserInfoService llUserInfoService;
	
	@Resource
	private LLMealSaleService llMealSaleService;
	
	@Resource
	private LLBaseInfoService llBaseInfoService;
	
	@Resource
	private BaseKeyService baseKeyService;
	
	/**
	 * 用户购买的时间进行上锁，防止他们进行并发购买的时候，出现问题
	 */
	private static Map<Long,Integer> userGouMaiQingKuang = new HashMap<>();
    
	
    public LLCaiGouInfo selectOne(Long id){
    	return dao.selectOne(id);
    }
	
	public List<LLCaiGouInfo> selectByIds(Collection<Long> ids){
		return dao.selectByIds(ids);
	}
	
	public List<LLCaiGouInfo> selectAll(){
		return dao.selectAll();
	}
	
	public List<LLCaiGouInfo> selectByWhere(Map<String, Object> parameters){
		return dao.selectByWhere(parameters);
	}
	
	
	public long selectCount(){
		return dao.selectCount();
	}
	
	public long selectCountByWhere(Map<String, Object> parameters){
		return dao.selectCountByWhere(parameters);
	}
	
	
	public boolean insert(LLCaiGouInfo obj){
		obj.setId(baseKeyService.getKey("ll_caigou_info"));
		Date date = new Date();
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		return dao.insert(obj);
	}
	
	public boolean insertInBatch(List<LLCaiGouInfo> objs){
		return dao.insertInBatch(objs);
	}
	
	
	public boolean update(LLCaiGouInfo obj){
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
    
    /**
     * 购买流量逻辑
     * @param sessionId 会话ids
     * @param id 购买的流量id
     * @return
     */
    public boolean goumaiLL(String sessionId,String id){
    	LLUserInfo user = llUserInfoService.selectBySessionId(sessionId);  	
    	LLBaseInfo baseinfo = llBaseInfoService.selectOne(NumberUtils.parseLong(id, 0l));
    	if(baseinfo == null){
			BusinessExceptionUtil.threwException("找不到该流量");
		}
    	//判断是否有余额
    	try {
    		lock(user.getId());
    		
    		LLMealSale dingdan = llMealSaleService.getSY(user.getId());
    		if(dingdan == null){
    			BusinessExceptionUtil.threwException("您已经没有余额,请联系我们客服进行购买");
    		}
    		//减余额
    		llMealSaleService.subSY(dingdan.getId());
    		
    		//增加购买详情
    		LLCaiGouInfo caigou = new LLCaiGouInfo();
    		caigou.setBaseId(baseinfo.getId());
    		caigou.setBaseSourceId(baseinfo.getSourceId());
    		caigou.setUserId(user.getId());
    		caigou.setIsmeal(1);
    		caigou.setSaleId(dingdan.getId());
    		caigou.setSessionid(sessionId);
    		caigou.setStatus(1);
    		insert(caigou);
    		
		}finally{
			unLock(user.getId());
		}
		return true;
	}
    
    
    /**
     * 进行上锁，防止同一个用户并发
     * @param userId
     */
    private void lock(Long userId){
    	userGouMaiQingKuang.put(userId, 1);
    }
    
    /**
     * 解锁
     * @param userId
     */
    private void unLock(Long userId){
    	userGouMaiQingKuang.remove(userId);
    }
    
    /**
     * 用户的累计购买流量
     * @param parameters
     * @return
     */
    public long getLeiJiLiuliang(Long userId){
    	Map<String, Object> parameters = MapUtils.genMap("status", 1, "userId", userId);
		long count = selectCountByWhere(parameters);
		return count;
	}
  
    /**
     * 设置购买的流量信息
     * @param parameters
     * @return
     */
    public List<LLCaiGouInfo> selectGouMai(Map<String, Object> parameters){
    	parameters.put("orderby", "ll_caigou_info.create_date desc");
    	List<LLCaiGouInfo> list = dao.selectGouMai(parameters);
    	if(CollectionUtils.isEmpty(list)){
    		return null;
    	}
    	List<Long> idList = new ArrayList<>();
    	for(LLCaiGouInfo goumai:list){
    		idList.add(goumai.getBaseId());
    	}
    	
    	List<LLBaseInfo> infoList = llBaseInfoService.selectByIds(idList);
    	Map<Long,LLBaseInfo> infoMap = new HashMap<>();
    	for(LLBaseInfo info:infoList){
    		infoMap.put(info.getId(), info);
    	}
    	//设置流量基本信息
    	for(LLCaiGouInfo goumai:list){
    		goumai.setLlBaseInfo(infoMap.get(goumai.getBaseId()));
    	}
    	 	
		return list;
	}
    
    /**
     * 进行计数
     * @param parameters
     * @return
     */
    public long selectGouMaiCount(Map<String, Object> parameters){
		return dao.selectGouMaiCount(parameters);
	}

}
