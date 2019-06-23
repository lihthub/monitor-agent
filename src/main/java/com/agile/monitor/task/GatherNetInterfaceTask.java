package com.agile.monitor.task;


import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.agile.monitor.dao.MonitorDao;
import com.agile.monitor.domain.NetInterface;
import com.agile.monitor.manager.IdWorker;
import com.agile.monitor.manager.OperatingSystemManager;

/**
 * 网卡收集任务
 * 
 * @author lihaitao
 * @since 2019-05-14
 */
@Component
@EnableScheduling
public class GatherNetInterfaceTask {
	
	private static final Logger log = LoggerFactory.getLogger(GatherNetInterfaceTask.class);
	
	@Autowired
	private OperatingSystemManager osManager;
	
	@Autowired
	private MonitorDao monitorDao;
	
	@Autowired
	private IdWorker idWorker;
	
	/**
	 * 每分钟的第0秒执行
	 */
	@Scheduled(cron = "0 0/1 * * * *")
	public void gather() {
		Date createTime = new Date();
		List<NetInterface> netInterfaceList = osManager.getNetInterfaceList();
		if (netInterfaceList != null) {
			for (NetInterface netInterface : netInterfaceList) {
				netInterface.setId(idWorker.nextId());
				netInterface.setCreateTime(createTime);
				try {
					monitorDao.insertNetInterface(netInterface);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}
	
}
