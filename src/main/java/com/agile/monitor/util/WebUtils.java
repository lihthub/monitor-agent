package com.agile.monitor.util;
 
import javax.servlet.http.HttpServletRequest;
 
/**
 * 获取远程请求客户端的外网IP工具
 * 
 * @author lihaitao
 * @since 2019-04-23
 */
 
public class WebUtils {
 
    /**
     * 获取远程请求客户端的外网IP
     */
	public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
}
