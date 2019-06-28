create table `monitor_server` (
	`id` bigint unsigned primary key,
	`ip` varchar(30) not null comment '服务器IP',
	`host_name` varchar(50) not null comment '服务器主机名',
	`port` varchar(10) not null comment 'Web接口访问端口',
	`os_name` varchar(50) not null comment '服务器操作系统',
	`description` varchar(50) comment '服务器描述',
	`create_time` datetime not null comment '创建时间',
	`modify_time` timestamp not null default current_timestamp
	   on update current_timestamp comment '修改时间'
) default charset=utf8 comment '监控服务器信息';

create table `monitor_cpu_percent` (
    `id` bigint unsigned primary key,
    `ip` varchar(30) not null comment '服务器IP',
    `user` double comment '用户使用率',
    `sys` double comment '系统使用率',
    `nice` double comment '当前成功率',
    `idle` double comment '当前空闲率',
    `wait` double comment '当前等待率',
    `create_time` datetime not null comment '创建时间'
) default charset=utf8 comment 'CPU使用率';

create table `monitor_memory` (
	`id` bigint unsigned primary key,
	`ip` varchar(30) not null comment '服务器IP',
	`total` bigint not null comment '内存总量',
	`used` bigint not null comment '内存使用',
	`free` bigint not null comment '内存剩余',
	`available` bigint not null comment '内存可用',
	`swap_total` bigint not null comment '交换总量',
	`swap_used` bigint not null comment '交换使用',
	`swap_free` bigint not null comment '交换剩余',
	`create_time` datetime not null comment '创建时间'
) default charset=utf8 comment '内存和交换';

create table `monitor_net_interface` (
    `id` bigint unsigned primary key,
    `ip` varchar(30) not null comment '服务器IP',
    `name` varchar(30) not null comment '网卡名称',
    `type` varchar(30) comment '网卡类型',
    `description` varchar(100) comment '网卡描述',
    `hwaddr` varchar(30) not null comment '网卡物理地址',
    `address` varchar(30) not null comment '网卡IP地址',
    `netmask` varchar(30) comment '子网掩码',
    `broadcast` varchar(30) comment '广播地址',
    `speed` bigint comment '线路速度',
    `rx_packets` bigint not null comment '接收的总包裹数',
    `rx_bytes` bigint not null comment '接收的总字节数',
    `tx_packets` bigint not null comment '发送的总包裹数',
    `tx_bytes` bigint not null comment '发送的总字节数',
    `create_time` datetime not null comment '创建时间'
) default charset=utf8 comment '网卡';

create table `monitor_local_disk` (
	`id` bigint unsigned primary key,
	`ip` varchar(30) not null comment '服务器IP',
	`dev_name` varchar(50) not null comment '设备名称',
	`dir_name` varchar(50) not null comment '磁盘目录',
	`sys_type_name` varchar(20) not null comment '文件系统',
	`total` bigint not null comment '总容量KB',
	`free` bigint not null comment '剩余容量KB',
	`used` bigint not null comment '已使用容量KB',
	`avail` bigint not null comment '可用容量KB',
	`disk_reads` bigint not null comment '读入',
	`disk_writes` bigint not null comment '写出',
	`disk_read_bytes` bigint not null comment '读入字节',
	`disk_write_bytes` bigint not null comment '写出字节',
	`use_percent` double not null comment '使用率',
	`create_time` datetime not null comment '创建时间'
) default charset=utf8 comment '本地磁盘';

create table `monitor_watch_process` (
	`id` bigint unsigned primary key,
	`ip` varchar(30) not null comment '服务器IP',
	`pid` varchar(20) not null comment '进程ID',
	`user` varchar(30) not null comment '用户',
	`start_time` varchar(20) not null comment '开始时间',
	`mem_size` varchar(20) not null comment '虚拟内存',
	`mem_rss` varchar(20) not null comment '实际内存',
	`mem_share` varchar(20) not null comment '共享内存',
	`state` varchar(20) not null comment '进程状态',
	`cpu_time` varchar(20) not null comment 'CPU时间',
	`name` varchar(100) not null comment '进程名',
	`create_time` datetime not null comment '创建时间'
) default charset=utf8 comment '监控进程';