package com.agile.monitor.context;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.agile.monitor.dao.MonitorDao;
import com.agile.monitor.domain.Server;
import com.agile.monitor.manager.IdWorker;
import com.agile.monitor.util.StringUtils;
import com.agile.monitor.util.Utils;

/**
 * 启动时收集服务器信息
 * 
 * @author lihaitao
 * @since 2019-05-07
 */
@Component
@Order
public class GatherServerRunner implements ApplicationRunner {
	
	@Autowired
	private MonitorDao monitorDao;
	
	@Autowired
	private IdWorker idWorker;
	
	@Value("${server.port}")
	private String port;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String ip = Utils.getHostAddress();
		String hostName = Utils.getHostName();
		String port = StringUtils.isNullOrEmpty(this.port) ? "8080" : this.port;
		String osName = System.getProperty("os.name");
		String osVersion = System.getProperty("os.version");
		if (!StringUtils.isNullOrEmpty(osName) && !StringUtils.isNullOrEmpty(osVersion)) {
			osName = osName + " (" + osVersion + ")";
		}
		
		if (!StringUtils.isNullOrEmpty(ip)) {
			Server getServer = monitorDao.getServer(ip);
			if (getServer != null) {
				Server updateServer = new Server();
				int count = 0;
				if (!Objects.equals(getServer.getHostName(), hostName)) {
					updateServer.setHostName(hostName);
					count++;
				}
				if (!Objects.equals(getServer.getPort(), port)) {
					updateServer.setPort(port);
					count++;
				}
				if (!Objects.equals(getServer.getOsName(), osName)) {
					updateServer.setOsName(osName);
					count++;
				}
				if (count > 0) {
					updateServer.setId(getServer.getId());
					monitorDao.updateServer(updateServer);
				}
			} else {
				Server server = new Server();
				server.setId(idWorker.nextId());
				server.setIp(ip);
				server.setHostName(hostName);
				server.setPort(port);
				server.setOsName(osName);
				monitorDao.insertServer(server);
			}
		}
	}

}
