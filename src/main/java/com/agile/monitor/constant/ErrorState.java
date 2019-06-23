package com.agile.monitor.constant;

/**
 * 错误信息枚举类
 *
 * @author lihaitao
 * @since 2019-03-24
 */
public enum ErrorState {
	
	ACCESS_DENIED("access_denied", "没有访问权限"),
	UNKNOWN("unknown", "未知错误");
	
	/** 错误码 */
	private String errorCode;
	
	/** 错误消息 */
	private String errorMsg;
	
	private ErrorState(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
	
	public String getErrorMsg() {
		return this.errorMsg;
	}
	
	@Override
	public String toString() {
		return this.errorCode;
	}
	
}
