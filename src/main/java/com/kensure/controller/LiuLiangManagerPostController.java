package com.kensure.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.exception.ParamUtils;
import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.http.RequestUtils;
import co.kensure.io.ExcelUtils;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.DateUtils;
import co.kensure.mem.MapUtils;
import co.kensure.mem.NumberUtils;

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.liuliang.model.LLBaseInfo;
import com.kensure.ktl.liuliang.model.LLMealInfo;
import com.kensure.ktl.liuliang.model.LLMealSale;
import com.kensure.ktl.liuliang.model.LLUserInfo;
import com.kensure.ktl.liuliang.service.LLBaseInfoService;
import com.kensure.ktl.liuliang.service.LLMealInfoService;
import com.kensure.ktl.liuliang.service.LLMealSaleService;
import com.kensure.ktl.liuliang.service.LLUserInfoService;

/**
 * 流量业务后台的逻辑处理
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "liuliangmanagerpost")
public class LiuLiangManagerPostController {

	@Resource
	private LLUserInfoService llUserInfoService;

	@Resource
	private LLBaseInfoService llBaseInfoService;

	@Resource
	private LLMealInfoService llMealInfoService;

	@Resource
	private LLMealSaleService llMealSaleService;

	/**
	 * 批量导入流量数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("importliuliang.do")
	public ResultInfo importliuliang(MultipartFile file, String sessionid, HttpServletRequest req, HttpServletResponse rep) {
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (!userinfo.isAdmin()) {
			BusinessExceptionUtil.threwException("用户权限错误");
		}
		int i = 0;
		List<LLBaseInfo> objs = new ArrayList<>();
		;
		try {
			String fileName = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			List<String[]> list = null;
			if (fileName.endsWith("xls")) {
				BusinessExceptionUtil.threwException("必须为xlsx格式");
			} else if (fileName.endsWith("xlsx")) {
				list = ExcelUtils.get2CellDataByXlsx(inputStream);
			}
			if (CollectionUtils.isEmpty(list)) {
				return new ResultRowInfo(0);
			}
			Date date = new Date();
			// 删除第一行
			list.remove(0);

			for (String[] row : list) {
				// 1编号 2姓名 3手机 4微信 5QQ 6地区 7年龄 8芝麻分
				// 9渠道 10申请设备 11申请时间 12利息 13借款方式 14还款金额 15负债情况16身份证
				String cell2 = row[1];
				if (StringUtils.isBlank(cell2)) {
					continue;
				}
				String cell3 = row[2];
				String cell4 = row[3];
				String cell5 = row[4];
				String cell6 = row[5];
				String cell7 = row[6];
				String cell8 = row[7];
				String cell9 = row[8];
				String cell10 = row[9];
				String cell11 = row[10];
				String cell12 = row[11];
				String cell13 = row[12];
				String cell14 = row[13];
				String cell15 = row[14];
				String cell16 = row[15];

				LLBaseInfo base = new LLBaseInfo();
				base.setName(cell2);
				base.setMobile(cell3);
				base.setWeixin(cell4);
				base.setQq(NumberUtils.parseLong(cell5, 0L) + "");
				base.setArea(cell6);
				base.setYear(NumberUtils.parseInteger(cell7, 0));
				base.setZhimafen(NumberUtils.parseInteger(cell8, 0));
				base.setQudao(cell9);
				base.setShebei(cell10);
				Date publishDate = DateUtils.parse(cell11, DateUtils.DAY_FORMAT);
				base.setPublishDate(publishDate);
				base.setLixi(cell12);
				base.setJkfs(cell13);
				base.setHkje(cell14);
				base.setFuzai(cell15);
				base.setCard(cell16);
				base.setCreateDate(date);
				base.setUpdateDate(date);
				base.setStatus(1);
				i++;
				objs.add(base);
			}
		} catch (Exception e) {
			BusinessExceptionUtil.threwException(e);
		}
		llBaseInfoService.insertInBatch(objs);
		return new ResultRowInfo(i);
	}

	/**
	 * 获取记录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("recordlist.do")
	public ResultInfo recordlist(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (!userinfo.isAdmin()) {
			BusinessExceptionUtil.threwException("用户权限错误");
		}

		String startTime = json.getString("startTime");
		String endTime = json.getString("endTime");
		String qudao = json.getString("qudao");
		String area = json.getString("area");
		String zhimafen = json.getString("zhimafen");

		String limit = json.getString("limit");
		String current = json.getString("current");

		Map<String, Object> parameters = new HashMap<>();
		if (StringUtils.isNotBlank(startTime)) {
			parameters.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			parameters.put("endTime", endTime);
		}
		if (StringUtils.isNotBlank(qudao)) {
			parameters.put("qudao", qudao);
		}
		if (StringUtils.isNotBlank(area)) {
			parameters.put("area", area);
		}
		if (StringUtils.isNotBlank(zhimafen)) {
			parameters.put("zhimafen", NumberUtils.parseInteger(zhimafen, 0));
		}

		Integer limitI = NumberUtils.parseInteger(limit, 20);
		Integer currentI = NumberUtils.parseInteger(current, 1);
		Integer offset = (currentI - 1) * limitI;
		parameters.put("limit", limitI);
		parameters.put("offset", offset);
		parameters.put("orderby", "create_date desc");

		List<LLBaseInfo> list = llBaseInfoService.selectByWhere(parameters);
		int size = (int) llBaseInfoService.selectCountByWhere(parameters);
		return new ResultRowsInfo(list, size);
	}

	/**
	 * 获取套餐记录
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("meallist.do")
	public ResultInfo meallist(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (!userinfo.isAdmin()) {
			BusinessExceptionUtil.threwException("用户权限错误");
		}

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("status", 1);
		parameters.put("orderby", "create_date");

		List<LLMealInfo> list = llMealInfoService.selectByWhere(parameters);
		return new ResultRowsInfo(list);
	}

	/**
	 * 根据id获取一条套餐信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("mealinfo.do")
	public ResultInfo mealinfo(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String id = json.getString("id");
		LLMealInfo mealInfo = llMealInfoService.selectOne(NumberUtils.parseLong(id, 0l));
		return new ResultRowInfo(mealInfo);
	}

	/**
	 * 销售一个套餐
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("mealsale.do")
	public ResultInfo mealsale(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (!userinfo.isAdmin()) {
			BusinessExceptionUtil.threwException("用户权限错误");
		}
		String id = json.getString("id");
		String userIdStr = json.getString("userId");
		String shuliangStr = json.getString("shuliang");
		ParamUtils.isBlankThrewException(userIdStr, "购买人不能为空");
		ParamUtils.isBlankThrewException(shuliangStr, "数量不能为空");
		llMealSaleService.sale(NumberUtils.parseLong(id, 0l), NumberUtils.parseLong(userIdStr, 0l), shuliangStr);
		return new ResultRowInfo();
	}

	/**
	 * 销售记录列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("mealsalelist.do")
	public ResultInfo mealsalelist(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (!userinfo.isAdmin()) {
			BusinessExceptionUtil.threwException("用户权限错误");
		}
		Map<String, Object> parameters = new HashMap<>();
		MapUtils.putPageInfo(parameters, json);
		parameters.put("orderby", "create_date desc");
		List<LLMealSale> list = llMealSaleService.selectByWhereByDict(parameters);
		long total = llMealSaleService.selectCountByWhere(parameters);
		return new ResultRowsInfo(list,total);
	}
	
	/**
	 * 确认收款
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("pay.do")
	public ResultInfo pay(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (!userinfo.isAdmin()) {
			BusinessExceptionUtil.threwException("用户权限错误");
		}
		Long id = json.getLong("id");
		llMealSaleService.pay(id);
		return new ResultRowInfo();
	}
	
	

	/**
	 * 获取用户记录列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("userlist.do")
	public ResultInfo userlist(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (!userinfo.isAdmin()) {
			BusinessExceptionUtil.threwException("用户权限错误");
		}

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("status", 1);
		parameters.put("orderby", "create_date");

		List<LLUserInfo> list = llUserInfoService.selectByWhere(parameters);
		list.remove(0);
		return new ResultRowsInfo(list);
	}
	
	/**
	 * 套餐新增
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("mealadd.do")
	public ResultInfo mealadd(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String sessionid = json.getString("sessionid");
		LLUserInfo userinfo = llUserInfoService.selectBySessionId(sessionid);
		if (!userinfo.isAdmin()) {
			BusinessExceptionUtil.threwException("用户权限错误");
		}
		String name = json.getString("name");
		String danjia = json.getString("danjia");
		String tiaoshu = json.getString("tiaoshu");
		String tuijian = json.getString("tuijian");
		String youxiaoqi = json.getString("youxiaoqi");
		ParamUtils.isBlankThrewException(name, "名称不能为空");
		ParamUtils.isNotIntegerThrewException(danjia, "单价必须为整数");
		ParamUtils.isNotIntegerThrewException(tiaoshu, "条数必须为整数");
		ParamUtils.isNotIntegerThrewException(youxiaoqi, "有效期必须为整数");
		
		LLMealInfo meal = new LLMealInfo();
		meal.setName(name);
		meal.setDanjia(danjia);
		meal.setTiaoshu(NumberUtils.parseInteger(tiaoshu, 0));
		meal.setYouxiaoqi(youxiaoqi);
		meal.setTuijian(NumberUtils.parseInteger(tuijian, 0));
		meal.setType(1);	
		meal.setJine(danjia);
		meal.setStatus(1);
		llMealInfoService.insert(meal);
		
		return new ResultRowInfo();
	}

}
