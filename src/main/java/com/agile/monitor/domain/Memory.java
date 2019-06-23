package com.agile.monitor.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 物理内存
 * 
 * @author lihaitao
 * @since 2019-04-23
 */
public class Memory implements Serializable {

	private static final long serialVersionUID = -7572187090141973036L;
	
	private Long id;
	
	/** 服务器ip */
	private String ip;
	
	/** 总量 */
	private Long total;
	
	/** 使用 */
	private Long used;
	
	/** 剩余 */
	private Long free;
	
	/** 可用 */
	private Long available;
	
	/** 交换总量 */
	private Long swapTotal;
	
	/** 交换使用 */
	private Long swapUsed;
	
	/** 交换剩余 */
	private Long swapFree;
	
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

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getUsed() {
		return used;
	}

	public void setUsed(Long used) {
		this.used = used;
	}

	public Long getFree() {
		return free;
	}

	public void setFree(Long free) {
		this.free = free;
	}

	public Long getAvailable() {
		return available;
	}

	public void setAvailable(Long available) {
		this.available = available;
	}

	public Long getSwapTotal() {
		return swapTotal;
	}

	public void setSwapTotal(Long swapTotal) {
		this.swapTotal = swapTotal;
	}

	public Long getSwapUsed() {
		return swapUsed;
	}

	public void setSwapUsed(Long swapUsed) {
		this.swapUsed = swapUsed;
	}

	public Long getSwapFree() {
		return swapFree;
	}

	public void setSwapFree(Long swapFree) {
		this.swapFree = swapFree;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Memory [id=" + id + ", ip=" + ip + ", total=" + total + ", used=" + used + ", free=" + free
				+ ", available=" + available + ", swapTotal=" + swapTotal + ", swapUsed=" + swapUsed + ", swapFree="
				+ swapFree + ", createTime=" + createTime + "]";
	}
	
}
