/*
 * 文件名称: RequestUtils.java
 * 版权信息: Copyright 2015-2017 jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-5-8
 * 修改内容: 
 */
package co.kensure.http;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * HttpServletRequest工具类
 * 
 * @author fankd created on 2017-5-8
 */
public class RequestUtils {

	/**
	 * 将request中的参数变成json
	 * 
	 * @param request
	 * @return
	 */
	public static JSONObject paramToJson(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String paraName = (String) enu.nextElement();
			json.put(paraName, request.getParameter(paraName));
		}
		return json;
	}

}
