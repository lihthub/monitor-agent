package com.agile.monitor.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.agile.monitor.constant.ErrorState;
import com.agile.monitor.util.StringUtils;
import com.agile.monitor.util.Utils;
import com.agile.monitor.util.WebUtils;
import com.agile.monitor.web.RestResponse;
import com.alibaba.fastjson.JSONObject;

/**
 * Web接口访问控制过滤器
 * 
 * @author lihaitao
 * @since 2019-04-23
 */
@Component
@WebFilter("/*")
public class AccessControlFilter implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(AccessControlFilter.class);
	
	@Value("${monitor.web.allow}")
	private String allow;
	
	@Value("${monitor.web.api-key}")
	private String apiKey;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest res = (HttpServletRequest) request;
		String remoteAddr = WebUtils.getRemoteAddr(res);
		boolean isAllow = false;
		allow = allow.replaceAll("\\s+", "");
		if (!StringUtils.isNullOrEmpty(remoteAddr)) {
			if (!StringUtils.isNullOrEmpty(allow)) {
				String[] ips = allow.split(",");
				for (String ip : ips) {
					if (Objects.equals(remoteAddr, ip.trim())) {
						isAllow = validateApiKey(request);
						break;
					}
				}
			} else {
				isAllow = validateApiKey(request);
			}
		}
		if (isAllow) {
			if (log.isDebugEnabled()) {
				log.debug("IP " + remoteAddr + " is allowed access");
			}
			chain.doFilter(request, response);
		} else {
			printJson(response, RestResponse.errorResponse(ErrorState.ACCESS_DENIED));
		}
	}
	
	private boolean validateApiKey(ServletRequest request) {
		String nonceStr = request.getParameter("nonceStr");
		String sign = request.getParameter("sign");
		Map<String, String> signParams = new HashMap<String, String>();
		signParams.put("nonceStr", nonceStr);
		if (Objects.equals(Utils.genSign(signParams, apiKey), sign)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 返回JSON格式参数
	 */
	private void printJson(ServletResponse response, RestResponse restRes) {
		response.setCharacterEncoding("UTF-8");
    	response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(JSONObject.toJSONString(restRes));
		} catch (IOException ie) {
			log.error("An input or output exception occurred when get PrintWriter object", ie);
		} finally {
			try {
				out.flush();
				out.close();
			} catch (NullPointerException npe) {
				log.error("An input or output exception occurred when get PrintWriter object", npe);
			}
		}
	}

}
