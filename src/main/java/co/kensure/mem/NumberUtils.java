/*
 * 文件名称: MapUtils.java
 * 版权信息: Copyright 2015-2017 jingshu technology Co., LTD. All right reserved.
 * ----------------------------------------------------------------------------------------------
 * 修改历史:
 * ----------------------------------------------------------------------------------------------
 * 修改原因: 新增
 * 修改人员: fankd
 * 修改日期: 2017-5-8
 * 修改内容: 
 */
package co.kensure.mem;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;



/**
 * number工具类
 * 
 * @author fankd created on 2017-5-8
 */
public class NumberUtils {

	/**
	 * 如果为空或者是0，返回true，不然返回false
	 * 
	 * @param obj
	 * @return
	 * @author fankd created on 2017-5-8
	 */
	public static boolean isZero(Number obj) {
		if (obj == null) {
			return true;
		}
		boolean ret = obj.intValue() == 0;
		return ret;
	}

	/**
	 * Long型做加法，如果obj为空，认为是0
	 * 
	 * @param obj
	 * @return
	 * @author fankd created on 2017-5-8
	 */
	public static Long longAdd(Long obj, int i) {
		Long ret = Long.valueOf(0);
		if (obj != null) {
			ret = obj + i;
		}
		return ret;
	}

	/**
	 * Integer型做加法，如果obj为空，认为是0
	 * 
	 * @param obj
	 * @return
	 * @author fankd created on 2017-5-8
	 */
	public static Integer integerAdd(Integer obj, int i) {
		Integer ret = Integer.valueOf(0);
		if (obj != null) {
			ret = obj + i;
		}
		return ret;
	}
	
	
	/**
	 * String转成Integer型，如果obj为空，返回默认值
	 * 
	 * @param obj
	 * @param defaultVal
	 * @return
	 * @author fankd created on 2017-5-8
	 */
	public static Integer parseInteger(String obj, Integer defaultVal) {
		if(StringUtils.isBlank(obj)){
			return defaultVal;
		}
		BigDecimal big = new BigDecimal(obj);
		Integer ret = big.intValue();
		return ret;
	}
	
	/**
	 * Integer型如果为空，返回默认值
	 * 
	 * @param obj
	 * @param defaultVal
	 * @return
	 * @author fankd created on 2017-5-8
	 */
	public static Integer defaultInteger(Integer obj, Integer defaultVal) {
		if(obj == null){
			return defaultVal;
		}
		return obj;
	}
	
	
	/**
	 * String转成Long型，如果obj为空，返回默认值
	 * 
	 * @param obj
	 * @param defaultVal
	 * @return
	 * @author fankd created on 2017-5-8
	 */
	public static Long parseLong(String obj, Long defaultVal) {
		if(StringUtils.isBlank(obj)){
			return defaultVal;
		}
		BigDecimal big = new BigDecimal(obj);
		Long ret = big.longValue();
		return ret;
	}

}
