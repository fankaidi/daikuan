package co.kensure.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * 业务参数处理类
 *
 * @author fankd
 */
public class ParamUtils {

	/**
	 * 如果参数为空，抛出异常
	 */
	public static void isBlankThrewException(String param, String e) throws BusinessException {
		if (StringUtils.isBlank(param)) {
			BusinessExceptionUtil.threwException(e);
		}
	}
	
	/**
	 * 如果参数为空，抛出异常
	 */
	public static void isBlankThrewException(MultipartFile[] param, String e) throws BusinessException {
		if (param == null || param.length == 0) {
			BusinessExceptionUtil.threwException(e);
		}
	}
	
	/**
	 * 如果参数不是整数，抛出异常
	 */
	public static void isNotIntegerThrewException(String param, String e) throws BusinessException {
		if (!StringUtils.isNumeric(param)) {
			BusinessExceptionUtil.threwException(e);
		}
	}

	
	/**
	 * 如果参数为空，抛出异常
	 */
	public static void isBlankThrewException(Long param, String e) throws BusinessException {
		if (param == null) {
			BusinessExceptionUtil.threwException(e);
		}
	}
	/**
	 * 如果参数为空，抛出异常
	 */
	public static void isBlankThrewException(Integer param, String e) throws BusinessException {
		if (param == null) {
			BusinessExceptionUtil.threwException(e);
		}
	}
	
	/**
	 * 如果会话id为空,抛出异常
	 */
	public static void checkSessionid(JSONObject json) throws BusinessException {
		String sessionid = getSessionid(json);
		isBlankThrewException(sessionid,"会话不能为空");
	}
	
	
	/**
	 * 获取会话id，统一会话id是sessionid
	 */
	public static String getSessionid(JSONObject json) throws BusinessException {
		String sessionid = json.getString("sessionid");
		return sessionid;
	}
	
	
	
}
