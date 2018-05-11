package com.kensure.controller;

import java.util.List;
import java.util.Map;

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
import co.kensure.mem.MapUtils;
import co.kensure.mem.PageInfo;

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.ly.model.Record;
import com.kensure.ktl.ly.model.Tag;
import com.kensure.ktl.ly.service.RecordService;
import com.kensure.ktl.ly.service.TagService;

/**
 * 旅游管理业务的逻辑处理
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "lymanagepost")
public class LYManagePostController {

	@Resource
	private TagService tagService;

	@Resource
	private RecordService recordService;

	/**
	 * 返回标签数据
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "taglist.do", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo taglist(HttpServletRequest req, HttpServletResponse rep) {
		Map<String, Object> parameters = MapUtils.genMap("orderby", " tagtypeid,dorder ");
		List<Tag> taglist = tagService.selectByWhere(parameters);
		return new ResultRowsInfo(taglist);
	}

	/**
	 * 返回记录列表数据
	 * 
	 * @param req
	 * @param rep
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "recordlist.do", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public ResultInfo recordlist(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		int pageNo = 1;//json.getInteger("pageNo");
		Integer tagId = json.getInteger("tagId");
		PageInfo pageinfo = new PageInfo(pageNo,100);
		Map<String, Object> parameters = MapUtils.genMap("offset",pageinfo.getPageOffset(),"limit",pageinfo.getPageSize(),"orderby", " id desc ");
		if(tagId != null){
			parameters.put("tagid", tagId+"");
		}
		
		List<Record> recordlist = recordService.selectByWhere(parameters);
		return new ResultRowsInfo(recordlist);
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
