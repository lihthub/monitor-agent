package com.agile.monitor.task;


import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.agile.monitor.dao.MonitorDao;

/**
 * 清除一个月前的数据
 * 
 * @author lihaitao
 * @since 2019-05-14
 */
@Component
@EnableScheduling
public class ClearDataTask {
	
	@Autowired
	private MonitorDao monitorDao;
	
	/**
	 * 每天00:00执行
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void clearData() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		Date before = calendar.getTime();
		if (monitorDao.countCpuPercent(before) > 0) {
			monitorDao.deleteCpuPercent(before);
		}
		if (monitorDao.countMemory(before) > 0) {
			monitorDao.deleteMemory(before);
		}
		if (monitorDao.countNetInterface(before) > 0) {
			monitorDao.deleteNetInterface(before);
		}
		if (monitorDao.countLocalDisk(before) > 0) {
			monitorDao.deleteLocalDisk(before);
		}
		if (monitorDao.countWatchProcess(before) > 0) {
			monitorDao.deleteWatchProcess(before);
		}
	}
	
}
