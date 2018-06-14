package com.kensure.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import co.kensure.frame.ResultInfo;
import co.kensure.frame.ResultRowInfo;
import co.kensure.frame.ResultRowsInfo;
import co.kensure.http.RequestUtils;
import co.kensure.mem.MapUtils;
import co.kensure.mem.NumberUtils;

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.yj.model.YJContent;
import com.kensure.ktl.yj.model.YJTitle;
import com.kensure.ktl.yj.service.YJContentService;
import com.kensure.ktl.yj.service.YJTitleService;

/**
 * 游记列表
 * 
 * @author fankaidi
 *
 */
@Controller
@RequestMapping(value = "yj")
public class YouJiController {

	@Resource
	private YJTitleService yJTitleService;

	@Resource
	private YJContentService yJContentService;

	/**
	 * 首页推荐
	 * 
	 * @param req
	 * @param rep
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "tuijian.do", produces = "application/json;charset=UTF-8")
	public ResultInfo tuijian(HttpServletRequest req, HttpServletResponse rep) {
		Map<String, Object> parameters = MapUtils.genMap("status", 1, "orderby", "dorder desc");
		List<YJTitle> list = yJTitleService.selectByWhere(parameters);
		return new ResultRowsInfo(list);
	}

	/**
	 * 游记内容
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "content.do", produces = "application/json;charset=UTF-8")
	public ResultInfo content(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String id = json.getString("id");
		List<YJContent> list = yJContentService.getContentByTitleId(NumberUtils.parseLong(id, 0l));
		return new ResultRowsInfo(list);
	}

	/**
	 * 查询内容
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "search.do", produces = "application/json;charset=UTF-8")
	public ResultInfo search(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		Map<String, Object> parameters = MapUtils.genMap("status", 1, "orderby", "dorder desc");
		MapUtils.putStrings(parameters, json, "name");
		List<YJTitle> list = yJTitleService.selectByWhere(parameters);
		return new ResultRowsInfo(list);
	}

	/**
	 * 主题列表，后台管理用，不用加状态
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "titlelist.do", produces = "application/json;charset=UTF-8")
	public ResultInfo titlelist(HttpServletRequest req, HttpServletResponse rep) {
		// JSONObject json = RequestUtils.paramToJson(req);
		Map<String, Object> parameters = MapUtils.genMap("orderby", "dorder desc");
		List<YJTitle> list = yJTitleService.selectByWhere(parameters);
		return new ResultRowsInfo(list);
	}

	/**
	 * 增加主题
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addTitle.do", produces = "application/json;charset=UTF-8")
	public ResultInfo addTitle(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		YJTitle obj = new YJTitle();
		obj.setName(json.getString("name"));
		yJTitleService.insert(obj);
		return new ResultRowInfo(obj);
	}

	/**
	 * 获取目录下图片
	 */
	@ResponseBody
	@RequestMapping("updateTitle.do")
	public ResultInfo getpics(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String name = json.getString("name");
		Long id = json.getLong("id");
		yJTitleService.updateName(id, name);
		return new ResultRowInfo();
	}

	/**
	 * 发布主题
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "fabu.do", produces = "application/json;charset=UTF-8")
	public ResultInfo fabu(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String idstr = json.getString("id");
		Long id = NumberUtils.parseLong(idstr, null);
		yJTitleService.fabu(id);
		return new ResultRowInfo();
	}

	/**
	 * 取消发布主题
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "quxiao.do", produces = "application/json;charset=UTF-8")
	public ResultInfo quxiao(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		String idstr = json.getString("id");
		Long id = NumberUtils.parseLong(idstr, null);
		yJTitleService.quxiao(id);
		return new ResultRowInfo();
	}

	/**
	 * 增加内容
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "addContent.do", produces = "application/json;charset=UTF-8")
	public ResultInfo addContent(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		Long id = json.getLong("id");
		String content = json.getString("content");
		yJTitleService.addContent(id, content);
		return new ResultRowInfo();
	}

	/**
	 * 批量导入图片
	 */
	@ResponseBody
	@RequestMapping("importpic.do")
	public ResultInfo importpic(MultipartFile[] file, HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		Long id = json.getLong("id");
		List<String> list = yJTitleService.importPic(id, file);
		return new ResultRowsInfo(list);
	}

	/**
	 * 设置主题图片
	 */
	@ResponseBody
	@RequestMapping("updatelogo.do")
	public ResultInfo updateLogo(MultipartFile file, HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		Long id = json.getLong("id");
		String url = json.getString("url");
		yJTitleService.updateLogo(id, url);
		return new ResultRowInfo();
	}

	/**
	 * 设置主题图片
	 */
	@ResponseBody
	@RequestMapping("copyyj.do")
	public ResultInfo copyYJ(HttpServletRequest req, HttpServletResponse rep) {
		JSONObject json = RequestUtils.paramToJson(req);
		Long id = json.getLong("id");
		yJTitleService.copyYJ(id);
		return new ResultRowInfo();
	}

}
