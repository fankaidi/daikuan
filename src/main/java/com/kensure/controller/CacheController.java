package com.kensure.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;

import com.kensure.mycom.base.service.BaseKeyService;
import com.kensure.mycom.config.service.MyConfigService;

/**
 * 缓存管理类
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "cache")
public class CacheController {

	@Resource
	private BaseKeyService baseKeyService;
	
	@Resource
	private MyConfigService myConfigService;

	/**
	 * 主键缓存清除
	 */
	@ResponseBody
	@RequestMapping(value = "bkClear.do", produces = "application/json;charset=UTF-8")
	public ResultInfo bkClear(HttpServletRequest req, HttpServletResponse rep) {
		baseKeyService.clearCache();
		return new ResultRowInfo();
	}

	/**
	 * r_config缓存重新初始化
	 */
	@ResponseBody
	@RequestMapping(value = "myConfigInit.do", produces = "application/json;charset=UTF-8")
	public ResultInfo myConfigInit(HttpServletRequest req, HttpServletResponse rep) {
		myConfigService.initCache();
		return new ResultRowInfo();
	}
	

}
