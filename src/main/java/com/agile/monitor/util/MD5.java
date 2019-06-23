package com.agile.monitor.util;

import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * MD5工具类
 * 
 * @author lihaitao
 * @since 2019-05-13
 */
public class MD5 {
	public static Log log = LogFactory.getLog(MD5.class);

	public final static String MD5Encode(String origin, String charsetname) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname)) {
				mdTemp.update(origin.getBytes());
			} else {
				mdTemp.update(origin.getBytes(charsetname));
			}
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			log.error("MD5Encode exception", e);
			return null;
		}
	}

}
