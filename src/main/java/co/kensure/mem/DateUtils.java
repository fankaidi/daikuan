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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * number工具类
 * 
 * @author fankd created on 2017-5-8
 */
public class DateUtils {

	public static String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static String DATE_FORMAT1 = "yyyyMMddHHmmss";

	/**
	 * 以默认格式掩码"yyyy-MM-dd HH:mm:ss"格式化日期
	 * 
	 * @param date
	 *            日期
	 * @return 日期字符串
	 * @author LuoJingtian created on 2012-2-29
	 * @since CDS Framework 1.0
	 */
	public static String format(Date date) {
		return format(date, DATE_FORMAT_PATTERN);
	}
	
	 
    /**
     * 以指定格式掩码格式化日期
     * @param date 日期
     * @param pattern 日期掩码
     * @return 日期字符串
     * @author LuoJingtian created on 2012-2-29 
     * @since CDS Framework 1.0
     */
    public static String format(Date date, String pattern) {
        if(null == date) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

}
