package com.agile.monitor.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 硬盘
 * 
 * @author lihaitao
 * @since 2019-04-18
 */
public class LocalDisk implements Serializable {
	
	private static final long serialVersionUID = 2228659735686779003L;
	
	private Long id;
	
	/** 服务器ip */
	private String ip;
	
	/** 磁盘名称 */
	private String devName;

	/** 磁盘目录 */
	private String dirName;
	
	/** 文件系统类型 */
	private String sysTypeName;
	
	/** 总容量 KB */
	private Long total;
	
	/** 剩余容量 KB */
	private Long free;
	
	/** 已使用容量 KB */
	private Long used;
	
	/** 可用容量 KB */
	private Long avail;
	
	/** 读入 */
	private Long diskReads;
	
	/** 写出 */
	private Long diskWrites;
	
	/** 读入字节 */
	private Long diskReadBytes;
	
	/** 写出字节 */
	private Long diskWriteBytes;
	
	/** 使用率 */
	private Double usePercent;
	
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

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getSysTypeName() {
		return sysTypeName;
	}

	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getFree() {
		return free;
	}

	public void setFree(long free) {
		this.free = free;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public long getAvail() {
		return avail;
	}

	public void setAvail(long avail) {
		this.avail = avail;
	}

	public long getDiskReads() {
		return diskReads;
	}

	public void setDiskReads(long diskReads) {
		this.diskReads = diskReads;
	}

	public long getDiskWrites() {
		return diskWrites;
	}

	public void setDiskWrites(long diskWrites) {
		this.diskWrites = diskWrites;
	}

	public long getDiskReadBytes() {
		return diskReadBytes;
	}

	public void setDiskReadBytes(long diskReadBytes) {
		this.diskReadBytes = diskReadBytes;
	}

	public long getDiskWriteBytes() {
		return diskWriteBytes;
	}

	public void setDiskWriteBytes(long diskWriteBytes) {
		this.diskWriteBytes = diskWriteBytes;
	}

	public double getUsePercent() {
		return usePercent;
	}

	public void setUsePercent(double usePercent) {
		this.usePercent = usePercent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "LocalDisk [id=" + id + ", ip=" + ip + ", devName=" + devName + ", dirName=" + dirName + ", sysTypeName="
				+ sysTypeName + ", total=" + total + ", free=" + free + ", used=" + used + ", avail=" + avail
				+ ", diskReads=" + diskReads + ", diskWrites=" + diskWrites + ", diskReadBytes=" + diskReadBytes
				+ ", diskWriteBytes=" + diskWriteBytes + ", usePercent=" + usePercent + ", createTime=" + createTime
				+ "]";
	}

}
