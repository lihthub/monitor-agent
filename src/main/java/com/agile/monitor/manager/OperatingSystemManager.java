package com.agile.monitor.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.hyperic.sigar.cmd.Ps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.agile.monitor.domain.CpuPercent;
import com.agile.monitor.domain.LocalDisk;
import com.agile.monitor.domain.Memory;
import com.agile.monitor.domain.NetInterface;
import com.agile.monitor.domain.WatchProcess;
import com.agile.monitor.util.ObjectUtils;
import com.agile.monitor.util.StringUtils;
import com.agile.monitor.util.Utils;

/**
 * 服务器内存、磁盘、CPU等信息获取
 * 
 * @author lihaitao
 * @since 2019-04-18
 */
@Component
public class OperatingSystemManager {
	
	private static final Logger log = LoggerFactory.getLogger(OperatingSystemManager.class);
	
	@Autowired
	private Sigar sigar;
	
	@Value("${monitor.watch-process}")
	private String watchProcess;
	
	/**
	 * 内存和交换
	 */
	public Memory getMemory() {
		Mem mem = null;
		Swap swap = null;
		try {
			mem = sigar.getMem();
			swap = sigar.getSwap();
		} catch (SigarException e) {
			log.error(e.getMessage(), e);
		}
		if (mem == null) {
			return null;
		}
		Memory memory = new Memory();
		memory.setIp(Utils.getHostAddress());
		memory.setTotal(mem.getTotal());
		memory.setUsed(mem.getUsed());
		memory.setFree(mem.getFree());
		memory.setAvailable(mem.getActualFree());
		if (swap != null) {
			memory.setSwapTotal(swap.getTotal());
			memory.setSwapUsed(swap.getUsed());
			memory.setSwapFree(swap.getFree());
		}
		return memory;
	}
	
	/**
	 * CPU
	 */
	public CpuInfo[] getCpuInfoList() {
		try {
			return sigar.getCpuInfoList();
		} catch (SigarException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * CPU使用率
	 */
	public CpuPerc[] getCpuPercList() {
		try {
			return sigar.getCpuPercList();
		} catch (SigarException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * CPU使用率
	 */
	public CpuPercent getCpuPercent() {
		CpuPerc cpuPerc = null;
		try {
			cpuPerc = sigar.getCpuPerc();
		} catch (SigarException e) {
			log.error(e.getMessage(), e);
		}
		if (cpuPerc == null) {
			return null;
		}
		CpuPercent cpuPercent = ObjectUtils.convertObject(cpuPerc, CpuPercent.class);
		if (cpuPercent != null) {
			cpuPercent.setIp(Utils.getHostAddress());
		}
		return cpuPercent;
	}
	
	/**
	 * 文件系统：本地磁盘，网络，闪存，光驱，页面交换区
	 */
	public FileSystem[] getFileSystemList() {
		try {
			return sigar.getFileSystemList();
		} catch (SigarException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 文件系统使用情况
	 * 
	 * @param dirName 目录名称
	 */
	public FileSystemUsage getFileSystemUsage(String dirName) {
		try {
			return sigar.getFileSystemUsage(dirName);
		} catch (SigarException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 磁盘列表
	 */
	public List<LocalDisk> getLocalDiskList() {
		List<LocalDisk> localDiskList = new ArrayList<LocalDisk>();
		FileSystem[] fileSystemList = getFileSystemList();
		if (fileSystemList != null) {
			for (FileSystem fileSystem : fileSystemList) {
				if (fileSystem.getType() == FileSystem.TYPE_LOCAL_DISK || "/".equals(fileSystem.getDirName())) {
					String dirName = fileSystem.getDirName();
					FileSystemUsage fileSystemUsage = getFileSystemUsage(dirName);
					LocalDisk localDiskUsage = ObjectUtils.convertObject(fileSystemUsage, LocalDisk.class);
					localDiskUsage.setIp(Utils.getHostAddress());
					localDiskUsage.setDevName(fileSystem.getDevName());
					localDiskUsage.setDirName(dirName);
					localDiskUsage.setSysTypeName(fileSystem.getSysTypeName());
					localDiskList.add(localDiskUsage);
				}
			}
		}
		return localDiskList;
	}
	
	/**
	 * 网卡配置
	 * 
	 * @param name 网卡名
	 */
	public NetInterfaceConfig getNetInterfaceConfig(String name) {
		try {
			return sigar.getNetInterfaceConfig(name);
		} catch (SigarException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 网卡状态
	 * 
	 * @param name 网卡名
	 */
	public NetInterfaceStat getNetInterfaceStat(String name) {
		try {
			return sigar.getNetInterfaceStat(name);
		} catch (SigarException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 网卡列表
	 */
	public List<NetInterface> getNetInterfaceList() {
		List<NetInterface> list = new ArrayList<NetInterface>();
		String[] names = null;
		try {
			names = sigar.getNetInterfaceList();
		} catch (SigarException e) {
			log.error(e.getMessage(), e);
		}
		if (names != null) {
			String ip = Utils.getHostAddress();
			for (String name : names) {
				NetInterface netInterface = null;
				NetInterfaceConfig config = getNetInterfaceConfig(name);
				if (config == null || (!"127.0.0.1".equals(config.getAddress())
						&& !Objects.equals(ip, config.getAddress()))) {
					continue;
				}
				netInterface = ObjectUtils.convertObject(config, NetInterface.class);
				if (netInterface != null) {
					NetInterfaceStat stat = getNetInterfaceStat(name);
					if (stat != null) {
						ObjectUtils.transferObject(stat, netInterface);
					}
					netInterface.setName(name);
					netInterface.setIp(ip);
					list.add(netInterface);
				}
			}
		}
		return list;
	}
	
	/**
	 * 进程列表
	 * 
	 * @param watchList 要监控的进程名列表
	 */
	public List<WatchProcess> getProcessList() {
		List<WatchProcess> procList = new ArrayList<WatchProcess>();
		long[] pids = null;
		try {
			pids = sigar.getProcList();
		} catch (SigarException e) {
			log.error(e.getMessage(), e);
		}
		if (pids == null) {
			return procList;
		}
		for (long pid : pids) {
			List<?> list = null;
			try {
				list = Ps.getInfo(sigar, pid);
			} catch (SigarException e) {
				if (log.isWarnEnabled()) {
					log.warn(e.getMessage(), e);
				}
			}
			if (list == null || list.size() <= 0) {
				continue;
			}
			String procName = String.valueOf(list.get(list.size() - 1));
			if (isWatchProcess(procName)) {
				procList.add(newWatchProcess(list));
			}
		}
		return procList;
	}
	
	/**
	 * 判断是否为需要监控的进程
	 * 
	 * @param procName 进程名称
	 * @return true or false
	 */
	private boolean isWatchProcess(String procName) {
		if (StringUtils.isNullOrEmpty(procName)) {
			return false;
		}
		
		String[] watchList = { "java", "mysql", "redis" };
    	if (!StringUtils.isNullOrEmpty(watchProcess)) {
    		watchProcess = watchProcess.replaceAll("\\s+", "");
    		watchList = watchProcess.split(",");
    	}
		for (String watch : watchList) {
			if (StringUtils.isNullOrEmpty(watch)) {
				continue;
			}
			if (procName.toLowerCase().contains(watch.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 创建进程监控对象
	 * 
	 * @param procInfo List<?>类型的进程信息，传入的procInfo必须不为null，否则会报NPE
	 * @return WatchProcess的实例
	 */
	private WatchProcess newWatchProcess(List<?> procInfo) {
		WatchProcess process = new WatchProcess();
		process.setIp(Utils.getHostAddress());
		for (int i = 0; i < procInfo.size(); i++) {
			String value = String.valueOf(procInfo.get(i));
			switch (i) {
				case 0: process.setPid(value); break;
				case 1: process.setUser(value); break;
				case 2: process.setStartTime(value); break;
				case 3: process.setMemSize(value); break;
				case 4: process.setMemRss(value); break;
				case 5: process.setMemShare(value); break;
				case 6: process.setState(value); break;
				case 7: process.setCpuTime(value); break;
				case 8: process.setName(value); break;
				default : break;
			}
		}
		return process;
	}  
	
}
