package com.agile.monitor.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agile.monitor.domain.CpuPercent;
import com.agile.monitor.domain.LocalDisk;
import com.agile.monitor.domain.Memory;
import com.agile.monitor.domain.NetInterface;
import com.agile.monitor.domain.WatchProcess;
import com.agile.monitor.manager.OperatingSystemManager;
import com.agile.monitor.web.RestResponse;

/**
 * 服务器监控
 * 
 * @author lihaitao
 * @since 2019-04-22
 */
@RestController
@RequestMapping("/")
public class MonitorController {
	
	@Autowired
	private OperatingSystemManager osManager;
	
	/**
	 * 服务器监控状态
	 */
    @RequestMapping("/getServerStatus")
	public RestResponse getServerStatus() {
    	CpuPercent cpuPercent = osManager.getCpuPercent();
    	RestResponse restRes = RestResponse.successResponse();
    	if (cpuPercent != null && cpuPercent.getUser() != null) {
    		restRes.put("status", true);
    	} else {
    		restRes.put("status", false);
    	}
        return restRes;
	}
	
	/**
	 * CPU使用率
	 */
    @RequestMapping("/getCpuPercent")
	public RestResponse getCpuPercent() {
    	CpuPercent cpuPercent = osManager.getCpuPercent();
    	RestResponse restRes = RestResponse.successResponse();
    	restRes.put("cpuPercent", cpuPercent);
        return restRes;
	}
    
    /**
	 * 内存和交换
	 */
    @RequestMapping("/getMemory")
	public RestResponse getMemory() {
    	Memory memory = osManager.getMemory();
    	RestResponse restRes = RestResponse.successResponse();
    	restRes.put("memory", memory);
        return restRes;
	}
    
    /**
	 * 网卡列表
	 */
    @RequestMapping("/listNetInterfaces")
	public RestResponse listNetInterfaces() {
    	List<NetInterface> netInterfaces = osManager.getNetInterfaceList();
    	RestResponse restRes = RestResponse.successResponse();
    	restRes.put("list", netInterfaces);
        return restRes;
	}
    
    /**
	 * 进程列表
	 */
    @RequestMapping("/listWatchProcesses")
	public RestResponse listWatchProcesses() {
    	List<WatchProcess> processes = osManager.getProcessList();
    	RestResponse restRes = RestResponse.successResponse();
    	restRes.put("list", processes);
        return restRes;
	}
	
	/**
	 * 硬盘列表
	 */
    @RequestMapping("/listLocalDisks")
    public RestResponse listLocalDisks(){
    	List<LocalDisk> localDisks = osManager.getLocalDiskList();
    	RestResponse restRes = RestResponse.successResponse();
    	restRes.put("list", localDisks);
        return restRes;
    }
    
}
