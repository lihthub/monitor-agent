package com.agile.monitor.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具类
 * 
 * @author lihaitao
 * @since 2019-04-23
 */
public class Utils {
	
	private static final Logger log = LoggerFactory.getLogger(Utils.class);
	
	/**
	 * 生成签名
	 * 
	 * @param params 参数
	 * @return 签名字符串
	 */
	public static String genSign(Map<String, String> params, String apiKey) {
		StringBuffer sb = new StringBuffer();
		// 参数名ASCII码从小到大排序（字典序）
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		// 使用URL键值对的格式（即key1=value1&key2=value2…）拼接成字符串
		for (String key : keys) {
			// 如果参数的值为空不参与签名
			if (params.get(key) != null && !(params.get(key).isEmpty())) {
				sb.append(key);
				sb.append('=');
				sb.append(params.get(key));
				sb.append('&');
			}
		}
		sb.append("key=");
		if (apiKey != null && !(apiKey.trim().isEmpty())) {
			sb.append(apiKey);
		}
		// 进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值
		String sign = MD5.MD5Encode(sb.toString(), "utf-8").toUpperCase();
		return sign;
	}
	
	/**
	 * 生成随机字符串
	 */
	public static String genNonceStr() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "").toLowerCase();
	}

	/**
	 * 获取本机IP地址
	 */
	public static String getHostAddress() {
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			log.error("Get host address error", e);
			return null;
		}
		if (inetAddress == null) {
			return null;
		}
        return inetAddress.getHostAddress();
	}
	
	/**
	 * 获取本地主机名
	 */
	public static String getHostName() {
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			log.error("Get host name error", e);
			return null;
		}
		if (inetAddress == null) {
			return null;
		}
		return inetAddress.getHostName();
	}
	
}
