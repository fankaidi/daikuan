/*
 * 文件名称: LLMealSaleServiceImpl.java
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

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.frame.JSBaseService;
import co.kensure.mem.ArithmeticUtils;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.DateUtils;
import co.kensure.mem.MapUtils;
import co.kensure.mem.NumberUtils;

import com.kensure.ktl.base.service.BaseKeyService;
import com.kensure.ktl.liuliang.dao.LLMealSaleDao;
import com.kensure.ktl.liuliang.model.LLMealInfo;
import com.kensure.ktl.liuliang.model.LLMealSale;
import com.kensure.ktl.liuliang.model.LLNotice;
import com.kensure.ktl.liuliang.model.LLUserInfo;

/**
 * 套餐订单信息服务实现类
 * 
 * @author fankd created on 2018-3-9
 * @since
 */
@Service
public class LLMealSaleService extends JSBaseService {

	@Resource
	private LLUserInfoService llUserInfoService;

	@Resource
	private LLMealInfoService llMealInfoService;

	@Resource
	private LLNoticeService llNoticeService;

	@Resource
	private BaseKeyService baseKeyService;

	@Resource
	private LLMealSaleDao dao;

	/**
	 * 对每个订单进行上锁，保证他做减法的时候不会并发
	 */
	private static Map<Long, Lock> mealSaleLock = new HashMap<>();

	public LLMealSale selectOne(Long id) {
		return dao.selectOne(id);
	}

	public List<LLMealSale> selectByIds(Collection<Long> ids) {
		return dao.selectByIds(ids);
	}

	public List<LLMealSale> selectAll() {
		return dao.selectAll();
	}

	public List<LLMealSale> selectByWhere(Map<String, Object> parameters) {
		return dao.selectByWhere(parameters);
	}

	public long selectCount() {
		return dao.selectCount();
	}

	public long selectCountByWhere(Map<String, Object> parameters) {
		return dao.selectCountByWhere(parameters);
	}

	public boolean insert(LLMealSale obj) {
		obj.setId(baseKeyService.getKey("ll_meal_sale"));
		Date date = new Date();
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		return dao.insert(obj);
	}

	public boolean insertInBatch(List<LLMealSale> objs) {
		return dao.insertInBatch(objs);
	}

	public boolean update(LLMealSale obj) {
		obj.setUpdateDate(new Date());
		return dao.update(obj);
	}

	public boolean updateByMap(Map<String, Object> params) {
		return dao.updateByMap(params);
	}

	public boolean delete(Long id) {
		return dao.delete(id);
	}

	public boolean deleteMulti(Collection<Long> ids) {
		return dao.deleteMulti(ids);
	}

	public boolean deleteByWhere(Map<String, Object> parameters) {
		return dao.deleteByWhere(parameters);
	}

	/**
	 * 套餐销售逻辑
	 * 
	 * @param mealId
	 * @param userId
	 * @param shuliangStr
	 * @return
	 */
	public boolean sale(Long mealId, Long userId, String shuliangStr) {
		// 进行前期校验
		LLUserInfo user = llUserInfoService.selectOne(userId);
		if (user == null) {
			BusinessExceptionUtil.threwException("购买人不能为空1");
		}
		int shuliang = NumberUtils.parseInteger(shuliangStr, 0);
		if (NumberUtils.isZero(shuliang)) {
			BusinessExceptionUtil.threwException("数量要大于0");
		}

		LLMealInfo mealInfo = llMealInfoService.selectOne(mealId);
		BigDecimal jine = ArithmeticUtils.mul(mealInfo.getDanjia(), shuliangStr);

		// 设置数据了
		LLMealSale mealsale = new LLMealSale();
		mealsale.setDanjia(mealInfo.getDanjia());
		mealsale.setMealId(mealInfo.getId());
		mealsale.setType(mealInfo.getType());
		mealsale.setUserId(userId);
		mealsale.setFenshu(shuliang);
		mealsale.setJine(jine.toPlainString());
		mealsale.setStatus(0);
		// 设置条数
		int total = mealInfo.getTiaoshu() * shuliang;
		mealsale.setSytiaoshu(total);
		mealsale.setTotal(total);
		// 设置份数
		int youxiaoqi = NumberUtils.parseInteger(mealInfo.getYouxiaoqi(), 0);
		Date validityDate = DateUtils.getPastMonth(new Date(), youxiaoqi);
		mealsale.setValidityDate(validityDate);
		insert(mealsale);
		return true;
	}

	/**
	 * 对数据查询之后，进行加工
	 * 
	 * @param parameters
	 * @return
	 */
	public List<LLMealSale> selectByWhereByDict(Map<String, Object> parameters) {
		List<LLMealSale> list = selectByWhere(parameters);
		for (LLMealSale sale : list) {
			LLUserInfo u = llUserInfoService.selectOne(sale.getUserId());
			LLMealInfo meal = llMealInfoService.selectOne(sale.getMealId());
			sale.setUserName(u.getName());
			sale.setMealName(meal.getName());
		}
		return list;
	}

	/**
	 * 确认付款
	 * 
	 * @param mealId
	 * 
	 * @return
	 */
	public boolean pay(Long id) {
		// 进行前期校验
		LLMealSale sale = selectOne(id);
		if (sale == null) {
			BusinessExceptionUtil.threwException("找不到信息");
		}
		if (sale.getStatus() != 0) {
			BusinessExceptionUtil.threwException("您已付款");
		}
		sale.setStatus(1);
		update(sale);
		LLNotice llnotice = new LLNotice();

		LLMealInfo meal = llMealInfoService.selectOne(sale.getMealId());
		llnotice.setTitle("您购买的套餐【" + meal.getName() + "】已经确认付款");
		llnotice.setUserId(sale.getUserId());
		llNoticeService.insert(llnotice);
		return true;
	}

	/**
	 * 获取一条有剩余的可购买记录
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	public LLMealSale getSY(Long userId) {
		LLUserInfo u = llUserInfoService.selectOne(userId);
		if (u == null) {
			BusinessExceptionUtil.threwException("找不到用户");
		}

		// 进行前期校验
		Map<String, Object> parameters = MapUtils.genMap("userId", userId, "status", 1, "sytiaoshu", 1, "orderby", "validity_date");
		List<LLMealSale> list = selectByWhere(parameters);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * 获取用户剩余的基础流量
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	public int getBaseTotalSY(Long userId) {
		int total = getTotalSY(userId, 2);
		return total;
	}

	/**
	 * 获取用户剩余的套餐流量
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	public int getMealTotalSY(Long userId) {
		int total = getTotalSY(userId, 1);
		return total;
	}

	/**
	 * 如果type为空，获取用户所有的剩余流量 type为1 套餐剩余流量 type为2 基础剩余流量
	 * 
	 * @param userId
	 * @param type
	 * @return
	 */
	public int getTotalSY(Long userId, Integer type) {
		// 进行前期校验
		Map<String, Object> parameters = MapUtils.genMap("userId", userId, "status", 1, "sytiaoshu", 1);
		if (type != null) {
			parameters.put("type", type);
		}
		List<LLMealSale> list = selectByWhere(parameters);
		if (CollectionUtils.isEmpty(list)) {
			return 0;
		}
		int total = 0;
		for (LLMealSale ms : list) {
			total += ms.getSytiaoshu();
		}
		return total;
	}

	/**
	 * 获取累计购买的基础流量条数
	 * 
	 * @param userId
	 * @return
	 */
	public int getBaseLeiJi(Long userId) {
		// 进行前期校验
		Map<String, Object> parameters = MapUtils.genMap("userId", userId, "status", 1, "type", 2);
		List<LLMealSale> list = selectByWhere(parameters);
		if (CollectionUtils.isEmpty(list)) {
			return 0;
		}
		int total = 0;
		for (LLMealSale ms : list) {
			total += ms.getTotal();
		}
		return total;
	}

	/**
	 * 获取累计购买的套餐个数
	 * 
	 * @param userId
	 * @return
	 */
	public int getMealLeiJi(Long userId) {
		// 进行前期校验
		Map<String, Object> parameters = MapUtils.genMap("userId", userId, "status", 1, "type", 1);
		List<LLMealSale> list = selectByWhere(parameters);
		if (CollectionUtils.isEmpty(list)) {
			return 0;
		}
		int total = 0;
		for (LLMealSale ms : list) {
			total += ms.getFenshu();
		}
		return total;
	}

	/**
	 * 消费一条剩余记录
	 * 
	 * @param id
	 *            ,购买记录id
	 * @param tiaoshu
	 *            ,购买条数
	 * 
	 * @return
	 */
	public void subSY(Long id, int tiaoshu) {
		try {
			lock(id);
			LLMealSale ms = selectOne(id);
			if (ms == null) {
				BusinessExceptionUtil.threwException("找不到该订单信息");
			}
			if (ms.getStatus() == 9) {
				BusinessExceptionUtil.threwException("该订单信息已经用完");
			}
			ms.setSytiaoshu(ms.getSytiaoshu() - tiaoshu);
			if (ms.getSytiaoshu() < 0) {
				BusinessExceptionUtil.threwException("该订单信息已经用完1");
			}
			if (ms.getSytiaoshu() == 0) {
				ms.setStatus(9);
			}
			update(ms);
		} finally {
			unLock(id);
		}

	}

	/**
	 * 进行上锁，防止同一个订单并发
	 * 
	 * @param userId
	 */
	private synchronized void lock(Long id) {
		Lock queueLock = mealSaleLock.get(id);
		if(queueLock == null){
			queueLock = new ReentrantLock();
		}
		queueLock.lock();
		mealSaleLock.put(id, queueLock);
	}

	/**
	 * 解锁
	 * 
	 * @param userId
	 */
	private void unLock(Long id) {
		Lock queueLock = mealSaleLock.get(id);
		mealSaleLock.remove(id);
		queueLock.unlock();	
	}
}
