/*
 * 文件名称: YJTitleServiceImpl.java
 * 版权信息: Copyright 2001-2017 hangzhou jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2018-4-19
 * 修改内容: 
 */
package com.kensure.ktl.yj.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import co.kensure.exception.BusinessExceptionUtil;
import co.kensure.exception.ParamUtils;
import co.kensure.frame.Const;
import co.kensure.frame.JSBaseService;
import co.kensure.io.FileUtils;
import co.kensure.io.ZipUtils;
import co.kensure.mem.CollectionUtils;
import co.kensure.mem.MapUtils;

import com.alibaba.fastjson.JSONObject;
import com.kensure.ktl.yj.dao.YJTitleDao;
import com.kensure.ktl.yj.model.YJContent;
import com.kensure.ktl.yj.model.YJTitle;
import com.kensure.mycom.base.service.BaseKeyService;

/**
 * 游记主表服务实现类
 * 
 * @author fankd created on 2018-4-19
 * @since
 */
@Service
public class YJTitleService extends JSBaseService {

	@Resource
	private YJTitleDao dao;

	@Resource
	private YJContentService yJContentService;

	@Resource
	private BaseKeyService baseKeyService;

	public YJTitle selectOne(Long id) {
		return dao.selectOne(id);
	}

	public List<YJTitle> selectByIds(Collection<Long> ids) {
		return dao.selectByIds(ids);
	}

	public List<YJTitle> selectAll() {
		return dao.selectAll();
	}

	public List<YJTitle> selectByWhere(Map<String, Object> parameters) {
		return dao.selectByWhere(parameters);
	}

	public long selectCount() {
		return dao.selectCount();
	}

	public long selectCountByWhere(Map<String, Object> parameters) {
		return dao.selectCountByWhere(parameters);
	}

	public boolean insert(YJTitle obj) {
		Long id = baseKeyService.getKey("yj_title");
		ParamUtils.isBlankThrewException(obj.getName(), "名称不能为空");
		Date date = new Date();
		obj.setId(id);
		obj.setCreateDate(date);
		obj.setUpdateDate(date);
		obj.setStatus(0);
		obj.setDorder(id.intValue());
		return dao.insert(obj);
	}

	public boolean insertInBatch(List<YJTitle> objs) {
		return dao.insertInBatch(objs);
	}

	public boolean update(YJTitle obj) {
		Date date = new Date();
		obj.setUpdateDate(date);
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
	 * 发布
	 * 
	 * @param id
	 */
	public void fabu(Long id) {
		ParamUtils.isBlankThrewException(id, "id不能为空");
		YJTitle obj = selectOne(id);
		obj.setStatus(1);
		update(obj);
	}

	/**
	 * 取消发布
	 * 
	 * @param id
	 */
	public void quxiao(Long id) {
		ParamUtils.isBlankThrewException(id, "id不能为空");
		YJTitle obj = selectOne(id);
		obj.setStatus(0);
		update(obj);
	}

	/**
	 * 更新内容
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void addContent(Long id, String content) {
		ParamUtils.isBlankThrewException(id, "id不能为空");
		ParamUtils.isBlankThrewException(content, "内容不能为空");
		List<YJContent> list = JSONObject.parseArray(content, YJContent.class);
		if (CollectionUtils.isEmpty(list)) {
			BusinessExceptionUtil.threwException("内容不能为空");
		}
		for (int i = 0; i < list.size(); i++) {
			YJContent yc = list.get(i);
			yc.setTitleId(id);
			yc.setDorder(i + 1);
		}
		// 先删除，再修改
		deleteContent(id);
		yJContentService.insertInBatch(list);
	}

	/**
	 * 删除一个主题下的所有内容
	 * 
	 * @param id
	 */
	private void deleteContent(Long id) {
		ParamUtils.isBlankThrewException(id, "id不能为空");
		Map<String, Object> parameters = MapUtils.genMap("titleId", id);
		yJContentService.deleteByWhere(parameters);
	}

	/**
	 * 导入图片
	 * 
	 * @param id
	 */
	public void importPic(Long id, MultipartFile file) {
		ParamUtils.isBlankThrewException(id, "id不能为空");
		String filePath = Const.ROOT_PATH + File.separator + id;
		String fullname = FileUtils.fileToIo(file, filePath, null);
		ZipUtils.unZip(fullname, filePath);
		FileUtils.delete(fullname);
	}
	
	/**
	 * 获取目录下图片
	 * 
	 * @param id
	 */
	public List<String> getPicList(Long id) {
		ParamUtils.isBlankThrewException(id, "id不能为空");
		String filePath = Const.ROOT_PATH + File.separator + id;
		String[] list = FileUtils.getChildList(filePath);
		List<String> pics = new ArrayList<>();
		if(list != null){
			for(String name:list){
				String url = Const.ROOT_URL+id+"/"+name;
				pics.add(url);
			}
		}	
		return pics;
	}
	
	/**
	 * 设置为主题图片
	 * 
	 * @param id
	 */
	public void updateLogo(Long id,String url) {
		ParamUtils.isBlankThrewException(id, "id不能为空");
		YJTitle title = selectOne(id);
		title.setPic(url);
		update(title);
	}

}
