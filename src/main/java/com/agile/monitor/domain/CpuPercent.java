package com.agile.monitor.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * CPU利用率
 * 
 * @author lihaitao
 * @since 2019-04-23
 */
public class CpuPercent implements Serializable {

	private static final long serialVersionUID = -4008038218771490724L;
	
	private Long id;
	
	/** 服务器ip */
	private String ip;
	
	/** 用户使用率 */
	private Double user;
	
	/** 系统使用率 */
	private Double sys;
	
	/** 当前成功率 */
	private Double nice;
	
	/** 当前空闲率 */
	private Double idle;
	
	/** 当前等待率 */
	private Double wait;
	
	/** 时间 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Double getUser() {
		return user;
	}

	public void setUser(Double user) {
		this.user = user;
	}

	public Double getSys() {
		return sys;
	}

	public void setSys(Double sys) {
		this.sys = sys;
	}

	public Double getNice() {
		return nice;
	}

	public void setNice(Double nice) {
		this.nice = nice;
	}

	public Double getIdle() {
		return idle;
	}

	public void setIdle(Double idle) {
		this.idle = idle;
	}

	public Double getWait() {
		return wait;
	}

	public void setWait(Double wait) {
		this.wait = wait;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CpuPercent [id=" + id + ", ip=" + ip + ", user=" + user + ", sys=" + sys + ", nice=" + nice + ", idle="
				+ idle + ", wait=" + wait + ", createTime=" + createTime + "]";
	}

}
