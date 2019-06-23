package com.agile.monitor.manager;

/**
 * 分布式ID生成器接口
 *
 * @author lihaitao
 * @since 2019-03-24
 */
public interface IdWorker {

	/**
	 * 获取下一个ID
	 * 
	 * @return id
	 */
	long nextId();
	
}
