package co.kensure.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务异常处理帮助类
 *
 * @author fankd
 */
public class ParamUtils {

	/**
	 * 如果参数为空，抛出异常
	 * 
	 * @param cause
	 * @throws BusinessException
	 */
	public static void isBlankThrewException(String param, String e) throws BusinessException {
		if (StringUtils.isBlank(param)) {
			BusinessExceptionUtil.threwException(e);
		}
	}
	
	/**
	 * 如果参数不是整数，抛出异常
	 * 
	 * @param cause
	 * @throws BusinessException
	 */
	public static void isNotIntegerThrewException(String param, String e) throws BusinessException {
		if (!StringUtils.isNumeric(param)) {
			BusinessExceptionUtil.threwException(e);
		}
	}

}
