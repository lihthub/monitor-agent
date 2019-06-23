package com.agile.monitor.util;

/**
 * 字符串工具类
 * 
 * @author lihaitao
 * @since 2019-03-26
 */
public class StringUtils {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}
	
}
