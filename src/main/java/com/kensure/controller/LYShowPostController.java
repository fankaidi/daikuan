package com.kensure.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.http.RequestUtils;

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.ly.model.Record;
import com.kensure.ktl.ly.model.TagType;
import com.kensure.ktl.ly.service.RecordService;
import com.kensure.ktl.ly.service.TagService;
import com.kensure.ktl.ly.service.TagTypeService;

/**
 * 旅游管理业务的逻辑处理
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "showpost")
public class LYShowPostController {

	@Resource
	private TagService tagService;
	
	@Resource
	private TagTypeService tagTypeService;

	@Resource
	private RecordService recordService;

	/**
	 * 返回目的地
	 * 
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "taglist.do", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo taglist(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String name = json.getString("name");
		String val = json.getString("val");
		List<TagType> tagtypelist = tagTypeService.selectByDesc(name,val);
		return new ResultRowsInfo(tagtypelist);
	}

	
	/**
	 * 返回记录数据
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "record.do", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo record(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		Long id = json.getLong("id");
		Record record = recordService.selectOne(id);
		return new ResultRowInfo(record);
	}
	
	/**
	 * 保存记录数据
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save.do", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo save(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		Record record = JSONObject.parseObject(json.toJSONString(), Record.class);
		recordService.insert(record);	
		return new ResultRowInfo(record);
	}

}
