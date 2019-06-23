package com.agile.monitor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.agile.monitor.manager.IdWorker;
import com.agile.monitor.manager.SnowflakeIdWorker;

/**
 * 分布式ID生成器配置类
 *
 * @author lihaitao
 * @since 2019-03-24
 */
@Configuration
public class IdWorkerConfig {

	/**
	 * 工作ID
	 */
	@Value("${monitor.idworker.worker-id}")
	private int workerId;
	
	/**
	 * 数据中心ID
	 */
	@Value("${monitor.idworker.datacenter-id}")
	private int datacenterId;
	
	@Bean
	public IdWorker idWorker() {
		return new SnowflakeIdWorker(workerId, datacenterId);
	}
	
}
