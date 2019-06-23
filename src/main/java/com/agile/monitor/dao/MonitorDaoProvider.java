package com.agile.monitor.dao;

import java.util.Objects;

import com.agile.monitor.domain.Server;
import com.agile.monitor.util.StringUtils;

/**
 * 构造SQL语句
 * 
 * @author lihaitao
 * @since 2019-05-14
 */
public class MonitorDaoProvider {
	
	public String getUpdateServerSQL(Server server) {
		Objects.requireNonNull(server, "Parameters must not be null");
		Objects.requireNonNull(server.getId(), "id must not be null");
		StringBuffer sql = new StringBuffer("update monitor_server set");
		if (!StringUtils.isNullOrEmpty(server.getHostName())) {
			sql.append(" host_name = '").append(server.getHostName()).append("',");
		}
		if (!StringUtils.isNullOrEmpty(server.getPort())) {
			sql.append(" port = '").append(server.getPort()).append("',");
		}
		if (!StringUtils.isNullOrEmpty(server.getOsName())) {
			sql.append(" os_name = '").append(server.getOsName()).append("',");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where id = '").append(server.getId()).append("'");
		return sql.toString();
	}
	
}