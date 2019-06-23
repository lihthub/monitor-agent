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
import com.agile.monitor.domain.WatchProcess;
import com.agile.monitor.manager.IdWorker;
import com.agile.monitor.manager.OperatingSystemManager;

/**
 * 监控进程
 * 
 * @author lihaitao
 * @since 2019-05-14
 */
@Component
@EnableScheduling
public class GatherWatchProcessTask {
	
	private static final Logger log = LoggerFactory.getLogger(GatherWatchProcessTask.class);
	
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
		List<WatchProcess> watchProcessList = osManager.getProcessList();
		if (watchProcessList != null) {
			for (WatchProcess watchProcess : watchProcessList) {
				watchProcess.setId(idWorker.nextId());
				watchProcess.setCreateTime(createTime);
				try {
					monitorDao.insertWatchProcess(watchProcess);
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}

}
