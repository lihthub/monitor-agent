package com.agile.monitor.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 监控进程
 * 
 * @author lihaitao
 * @since 2019-04-23
 */
public class WatchProcess implements Serializable {

	private static final long serialVersionUID = -8676004251736534973L;
	
	private Long id;
	
	/** 服务器ip */
	private String ip;
	
	/** 进程id */
	private String pid;
	
	/** 用户 */
	private String user;
	
	/** 开始时间 */
	private String startTime;
	
	/** 虚拟内存 */
	private String memSize;
	
	/** 实际内存 */
	private String memRss;
	
	/** 共享内存 */
	private String memShare;
	
	/** 状态 */
	private String state;
	
	/** CPU时间 */
	private String cpuTime;
	
	/** 进程名 */
	private String name;
	
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getMemSize() {
		return memSize;
	}

	public void setMemSize(String memSize) {
		this.memSize = memSize;
	}

	public String getMemRss() {
		return memRss;
	}

	public void setMemRss(String memRss) {
		this.memRss = memRss;
	}

	public String getMemShare() {
		return memShare;
	}

	public void setMemShare(String memShare) {
		this.memShare = memShare;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCpuTime() {
		return cpuTime;
	}

	public void setCpuTime(String cpuTime) {
		this.cpuTime = cpuTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "WatchProcess [id=" + id + ", ip=" + ip + ", pid=" + pid + ", user=" + user + ", startTime=" + startTime
				+ ", memSize=" + memSize + ", memRss=" + memRss + ", memShare=" + memShare + ", state=" + state
				+ ", cpuTime=" + cpuTime + ", name=" + name + ", createTime=" + createTime + "]";
	}

}
