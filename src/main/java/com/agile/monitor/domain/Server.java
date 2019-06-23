package com.agile.monitor.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 监控服务器
 * 
 * @author lihaitao
 * @since 2019-04-22
 */
public class Server implements Serializable {

	private static final long serialVersionUID = 691364359489806179L;

	private Long id;
	
	/** 服务器ip */
	private String ip;
	
	/** 服务器主机名 */
	private String hostName;
	
	/** web接口访问端口 */
	private String port;
	
	/** 服务器操作系统 */
	private String osName;
	
	/** 服务器描述 */
	private String description;
	
	/** 监控状态，true正常，false异常 */
	private Boolean status;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 修改时间 */
	private Date modifyTime;

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

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", ip=" + ip + ", hostName=" + hostName + ", port=" + port + ", osName=" + osName
				+ ", description=" + description + ", status=" + status + ", createTime=" + createTime + ", modifyTime="
				+ modifyTime + "]";
	}

}
