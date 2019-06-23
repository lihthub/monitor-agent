package com.agile.monitor.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.agile.monitor.domain.CpuPercent;
import com.agile.monitor.domain.LocalDisk;
import com.agile.monitor.domain.Memory;
import com.agile.monitor.domain.NetInterface;
import com.agile.monitor.domain.Server;
import com.agile.monitor.domain.WatchProcess;

/**
 * 监控数据持久化
 * 
 * @author lihaitao
 * @since 2019-04-23
 */
@Mapper
public interface MonitorDao {
	
	@Insert("insert into monitor_server (id, ip, host_name, port, os_name, description, create_time) values (#{id}, #{ip}, #{hostName}, #{port}, #{osName}, #{description}, now())")
	void insertServer(Server server);
	
	@Insert("insert into monitor_memory (id, ip, total, used, free, available, swap_total, swap_used, swap_free, create_time) values (#{id}, #{ip}, #{total}, #{used}, #{free}, #{available}, #{swapTotal}, #{swapUsed}, #{swapFree}, #{createTime})")
	void insertMemory(Memory memory);
	
	@Insert("insert into monitor_cpu_percent (id, ip, user, sys, nice, idle, wait, create_time) values (#{id}, #{ip}, #{user}, #{sys}, #{nice}, #{idle}, #{wait}, #{createTime})")
	void insertCpuPercent(CpuPercent cpuPercent);
	
	@Insert("insert into monitor_local_disk (id, ip, dev_name, dir_name, sys_type_name, total, free, used, avail, disk_reads, disk_writes, disk_read_bytes, disk_write_bytes, use_percent, create_time) values (#{id}, #{ip}, #{devName}, #{dirName}, #{sysTypeName}, #{total}, #{free}, #{used}, #{avail}, #{diskReads}, #{diskWrites}, #{diskReadBytes}, #{diskWriteBytes}, #{usePercent}, #{createTime})")
	void insertLocalDisk(LocalDisk localDisk);
	
	@Insert("insert into monitor_watch_process (id, ip, pid, user, start_time, mem_size, mem_rss, mem_share, state, cpu_time, name, create_time) values (#{id}, #{ip}, #{pid}, #{user}, #{startTime}, #{memSize}, #{memRss}, #{memShare}, #{state}, #{cpuTime}, #{name}, #{createTime})")
	void insertWatchProcess(WatchProcess watchProcess);
	
	@Insert("insert into monitor_net_interface (id, ip, name, type, description, hwaddr, address, netmask, broadcast, speed, rx_packets, rx_bytes, tx_packets, tx_bytes, create_time) values (#{id}, #{ip}, #{name}, #{type}, #{description}, #{hwaddr}, #{address}, #{netmask}, #{broadcast}, #{speed}, #{rxPackets}, #{rxBytes}, #{txPackets}, #{txBytes}, #{createTime})")
	void insertNetInterface(NetInterface netInterface);
	
	@UpdateProvider(type = MonitorDaoProvider.class, method = "getUpdateServerSQL")
	void updateServer(Server server);
	
	@Results({
		@Result(property = "id", column = "id", jdbcType = JdbcType.BIGINT),
		@Result(property = "ip", column = "ip", jdbcType = JdbcType.VARCHAR),
		@Result(property = "hostName", column = "host_name", jdbcType = JdbcType.VARCHAR),
		@Result(property = "port", column = "port", jdbcType = JdbcType.VARCHAR),
		@Result(property = "osName", column = "os_name", jdbcType = JdbcType.VARCHAR),
		@Result(property = "description", column = "description", jdbcType = JdbcType.VARCHAR),
		@Result(property = "createTime", column = "create_time", jdbcType = JdbcType.TIME),
		@Result(property = "modifyTime", column = "modify_time", jdbcType = JdbcType.TIMESTAMP)
	})
	@Select("select * from monitor_server where ip = #{ip}")
	Server getServer(String ip);
	
	@Delete("delete from monitor_memory where create_time < #{before}")
	void deleteMemory(Date before);
	
	@Delete("delete from monitor_cpu_percent where create_time < #{before}")
	void deleteCpuPercent(Date before);
	
	@Delete("delete from monitor_local_disk where create_time < #{before}")
	void deleteLocalDisk(Date before);
	
	@Delete("delete from monitor_watch_process where create_time < #{before}")
	void deleteWatchProcess(Date before);
	
	@Delete("delete from monitor_net_interface where create_time < #{before}")
	void deleteNetInterface(Date before);
	
	@Select("select count(*) from monitor_memory where create_time < #{before}")
	Integer countMemory(Date before);
	
	@Select("select count(*) from monitor_cpu_percent where create_time < #{before}")
	Integer countCpuPercent(Date before);
	
	@Select("select count(*) from monitor_local_disk where create_time < #{before}")
	Integer countLocalDisk(Date before);
	
	@Select("select count(*) from monitor_watch_process where create_time < #{before}")
	Integer countWatchProcess(Date before);
	
	@Select("select count(*) from monitor_net_interface where create_time < #{before}")
	Integer countNetInterface(Date before);
	
}
