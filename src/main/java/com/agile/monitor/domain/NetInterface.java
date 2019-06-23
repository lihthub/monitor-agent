package com.agile.monitor.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 网卡
 * 
 * @author lihaitao
 * @since 2019-04-24
 */
public class NetInterface implements Serializable {

	private static final long serialVersionUID = 242875555749533130L;
	
	private Long id;
	
	/** 服务器ip */
	private String ip;
	
	/** 网卡名称 */
	private String name;
	
	/** 网卡类型 */
	private String type;
	
	/** 网卡描述 */
	private String description;
	
	/** 物理地址 */
	private String hwaddr;
	
	/** 网卡IP地址 */
	private String address;
	
	/** 子网掩码 */
	private String netmask;
	
	/** 广播地址 */
	private String broadcast;
	
	/** 线路速度 */
	private Long speed;
	
	/** 接收的总包裹数 */
	private Long rxPackets;
	
	/** 接收的总字节数 */
	private Long rxBytes;
	
	/** 发送的总包裹数 */
	private Long txPackets;
	
	/** 发送的总字节数 */
	private Long txBytes;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHwaddr() {
		return hwaddr;
	}

	public void setHwaddr(String hwaddr) {
		this.hwaddr = hwaddr;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getBroadcast() {
		return broadcast;
	}

	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}

	public Long getSpeed() {
		return speed;
	}

	public void setSpeed(Long speed) {
		this.speed = speed;
	}

	public Long getRxPackets() {
		return rxPackets;
	}

	public void setRxPackets(Long rxPackets) {
		this.rxPackets = rxPackets;
	}

	public Long getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(Long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public Long getTxPackets() {
		return txPackets;
	}

	public void setTxPackets(Long txPackets) {
		this.txPackets = txPackets;
	}

	public Long getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(Long txBytes) {
		this.txBytes = txBytes;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "NetInterface [id=" + id + ", ip=" + ip + ", name=" + name + ", type=" + type + ", description="
				+ description + ", hwaddr=" + hwaddr + ", address=" + address + ", netmask=" + netmask + ", broadcast="
				+ broadcast + ", speed=" + speed + ", rxPackets=" + rxPackets + ", rxBytes=" + rxBytes + ", txPackets="
				+ txPackets + ", txBytes=" + txBytes + ", createTime=" + createTime + "]";
	}

}
