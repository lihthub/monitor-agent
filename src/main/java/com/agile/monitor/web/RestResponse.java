package com.agile.monitor.web;

import java.util.HashMap;

import com.agile.monitor.constant.ErrorState;

/**
 * controller层返回前端的参数封装对象
 *
 * @author lihaitao
 * @since 2019-03-25
 */
public class RestResponse extends HashMap<String, Object> {

	private static final long serialVersionUID = -3877541045237478264L;

	private RestResponse() {
	}

	/**
	 * 创建请求成功返回对象
	 * 
	 * @param user 用户信息
	 * @return
	 */
	public static RestResponse successResponse() {
		RestResponse restResponse = new RestResponse();
		restResponse.put("success", true);
		return restResponse;
	}

	/**
	 * 创建请求失败返回对象
	 * 
	 * @param error 错误信息
	 * @return
	 */
	public static RestResponse errorResponse(ErrorState error) {
		RestResponse restResponse = new RestResponse();
		restResponse.put("success", false);
		restResponse.put("errorCode", error.getErrorCode());
		restResponse.put("errorMsg", error.getErrorMsg());
		return restResponse;
	}

	/**
	 * 创建请求失败返回对象
	 * 
	 * @param errorMsg 错误信息
	 * @return
	 */
	public static RestResponse errorResponse(String errorCode, String errorMsg) {
		RestResponse restResponse = new RestResponse();
		restResponse.put("success", false);
		restResponse.put("errorCode", errorCode);
		restResponse.put("errorMsg", errorMsg);
		return restResponse;
	}

	/**
	 * 是否成功
	 */
	public boolean isSuccess() {
		Object success = this.get("success");
		if (success != null && success instanceof Boolean) {
			return (Boolean) success;
		}
		return false;
	}

}
