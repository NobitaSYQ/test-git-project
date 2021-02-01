Table of Contents
=================

* [tracker](#tracker)
    * [disabled](#disabled)
    * [bind_addr](#bind_addr)
    * [port](#port)
    * [connect_timeout](#connect_timeout)
    * [network_timeout](#network_timeout)
    * [base_path](#base_path)
    * [max_connections](#max_connections)
    * [accept_threads](#accept_threads)
    * [work_threads](#work_threads)
    * [store_lookup](#store_lookup)
    * [store_group](#store_group)
    * [store_server](#store_server)
    * [store_path](#store_path)
    * [download_server](#download_server)
    * [reserved_storage_space](#reserved_storage_space)
    * [log_level](#log_level)
    * [run_by_group](#run_by_group)
    * [run_by_user](#run_by_user)
    * [allow_hosts](#allow_hosts)
    * [sync_log_buff_interval](#sync_log_buff_interval)
    * [check_active_interval](#check_active_interval)
    * [thread_stack_size](#thread_stack_size)
    * [storage_ip_changed_auto_adjust](#storage_ip_changed_auto_adjust)
    * [storage_sync_file_max_delay](#storage_sync_file_max_delay)
    * [storage_sync_file_max_time](#storage_sync_file_max_time)
    * [use_trunk_file](#use_trunk_file)
    * [slot_min_size](#slot_min_size)
    * [slot_max_size](#slot_max_size)
    * [trunk_file_size](#trunk_file_size)
    * [trunk_create_file_advance](#trunk_create_file_advance)
    * [trunk_create_file_time_base](#trunk_create_file_time_base)
    * [trunk_create_file_interval](#trunk_create_file_interval)
    * [trunk_create_file_space_threshold](#trunk_create_file_space_threshold)
    * [trunk_init_check_occupying](#trunk_init_check_occupying)
    * [trunk_init_reload_from_binlog](#trunk_init_reload_from_binlog)
    * [trunk_compress_binlog_min_interval](#trunk_compress_binlog_min_interval)
    * [use_storage_id](#use_storage_id)
    * [storage_ids_filename](#storage_ids_filename)
    * [id_type_in_filename](#id_type_in_filename)
    * [store_slave_file_use_link](#store_slave_file_use_link)
    * [rotate_error_log](#rotate_error_log)
    * [error_log_rotate_time](#error_log_rotate_time)
    * [rotate_error_log_size](#rotate_error_log_size)
    * [log_file_keep_days](#log_file_keep_days)
    * [use_connection_pool](#use_connection_pool)
    * [connection_pool_max_idle_time](#connection_pool_max_idle_time)
    * [http.server_port](#httpserver_port)
    * [http.check_alive_interval](#httpcheck_alive_interval)
    * [http.check_alive_type](#httpcheck_alive_type)
    * [http.check_alive_uri](#httpcheck_alive_uri)
* [storage](#storage)
    * [disabled](#disabled)
    * [group_name](#group_name)
    * [bind_addr](#bind_addr)
    * [client_bind](#client_bind)
    * [port](#port)
    * [connect_timeout](#connect_timeout)
    * [network_timeout](#network_timeout)
    * [heart_beat_interval](#heart_beat_interval)
    * [stat_report_interval](#stat_report_interval)
    * [base_path](#base_path)
    * [max_connections](#max_connections)
    * [buff_size](#buff_size)
    * [accept_threads](#accept_threads)
    * [work_threads](#work_threads)
    * [disk_rw_separated](#disk_rw_separated)
    * [disk_reader_threads](#disk_reader_threads)
    * [disk_writer_threads](#disk_writer_threads)
    * [sync_wait_msec](#sync_wait_msec)
    * [sync_interval](#sync_interval)
    * [sync_start_time](#sync_start_time)
    * [sync_end_time](#sync_end_time)
    * [write_mark_file_freq](#write_mark_file_freq)
    * [store_path_count](#store_path_count)
    * [store_path0](#store_path0)
    * [subdir_count_per_path](#subdir_count_per_path)
    * [tracker_server](#tracker_server)
    * [log_level](#log_level)
    * [run_by_group](#run_by_group)
    * [run_by_user](#run_by_user)
    * [allow_hosts](#allow_hosts)
    * [file_distribute_path_mode](#file_distribute_path_mode)
    * [file_distribute_rotate_count](#file_distribute_rotate_count)
    * [fsync_after_written_bytes](#fsync_after_written_bytes)
    * [sync_log_buff_interval](#sync_log_buff_interval)
    * [sync_binlog_buff_interval](#sync_binlog_buff_interval)
    * [sync_stat_file_interval](#sync_stat_file_interval)
    * [thread_stack_size](#thread_stack_size)
    * [upload_priority](#upload_priority)
    * [if_alias_prefix](#if_alias_prefix)
    * [check_file_duplicate](#check_file_duplicate)
    * [file_signature_method](#file_signature_method)
    * [key_namespace](#key_namespace)
    * [keep_alive](#keep_alive)
    * [use_access_log](#use_access_log)
    * [rotate_access_log](#rotate_access_log)
    * [access_log_rotate_time](#access_log_rotate_time)
    * [rotate_error_log ](#rotate_error_log )
    * [error_log_rotate_time](#error_log_rotate_time)
    * [rotate_access_log_size](#rotate_access_log_size)
    * [rotate_error_log_size](#rotate_error_log_size)
    * [log_file_keep_days](#log_file_keep_days)
    * [file_sync_skip_invalid_record](#file_sync_skip_invalid_record)
    * [use_connection_pool](#use_connection_pool)
    * [connection_pool_max_idle_time](#connection_pool_max_idle_time)
    * [http.domain_name](#httpdomain_name)
    * [http.server_port](#httpserver_port)
* [client](#client)

## tracker
#### disabled
default: false

Is this config file disabled? false for enabled, true for disabled.

[Back to TOC](#table-of-contents)

#### bind_addr
default: INADDR_ANY

Bind an address of this host. Empty for bind all addresses of this host.

[Back to TOC](#table-of-contents)

#### port
default: 22000

The tracker server port.

[Back to TOC](#table-of-contents)

#### connect_timeout
default: 30s

Connect timeout in seconds.

[Back to TOC](#table-of-contents)

#### network_timeout
default: 30s

Network timeout in seconds.

[Back to TOC](#table-of-contents)

#### base_path
default: --

The base path to store data and log files. (must have)

[Back to TOC](#table-of-contents)

#### max_connections
default: 256

Max concurrent connections this server supported.

[Back to TOC](#table-of-contents)

#### accept_threads
default: 1

Accept thread count.

[Back to TOC](#table-of-contents)

#### work_threads
default: 4

Work thread count, should <= max_connections.

[Back to TOC](#table-of-contents)

#### store_lookup
default: 0

The method of selecting group to upload files.
*   0: round robin
*   1: specify group
*   2: load balance, select the max free space group to upload file

[Back to TOC](#table-of-contents)

#### store_group
default: --

Which group to upload file. When store_lookup set to 1, must set store_group to the group name.

[Back to TOC](#table-of-contents)

#### store_server
default: 0

Which storage server to upload file.
*   0: round robin
*   1: the first server order by ip address
*   2: the first server order by priority (the minimal)

[Back to TOC](#table-of-contents)

#### store_path
default: 0

Which path(means disk or mount point) of the storage server to upload file.
*   0: round robin
*   2: load balance, select the max free space path to upload file

[Back to TOC](#table-of-contents)

#### download_server
default: 0

Which storage server to download file.
*   0: round robin
*   1: the source storage server which the current file uploaded to

[Back to TOC](#table-of-contents)

#### reserved_storage_space 
default: 

Reserved storage space for system or other applications. If the free(available) space of any stoarge server in a group <= reserved_storage_space, no file can be uploaded to this group. bytes unit can be one of follows:

*   G or g for gigabyte(GB)
*   M or m for megabyte(MB)
*   K or k for kilobyte(KB)
*   no unit for byte(B)
*   XX.XX% as ratio such as reserved_storage_space = 10%

[Back to TOC](#table-of-contents)

#### log_level
default: info

Standard log level as syslog, case insensitive, value list:
* emerg for emergency
* alert
* crit for critical
* error
* warn for warning
* notice
* info
* debug

[Back to TOC](#table-of-contents)

#### run_by_group
default: --

Unix group name to run this program, not set (empty) means run by the group of current user.

[Back to TOC](#table-of-contents)

#### run_by_user
default: --

Unix user name to run this program, not set (empty) means run by current user.

[Back to TOC](#table-of-contents)

#### allow_hosts
default: * (any addresses)

"allow_hosts" can ocur more than once, host can be hostname or ip address, "*" means match all ip addresses, can use range like this: 10.0.1.[1-15,20] or host[01-08,20-25].domain.com, for example:
*   allow_hosts=10.0.1.[1-15,20]
*   allow_hosts=host[01-08,20-25].domain.com

[Back to TOC](#table-of-contents)

#### sync_log_buff_interval 
default: 10s

Sync log buff to disk every interval seconds.

[Back to TOC](#table-of-contents)

#### check_active_interval 
default: 100s

Check storage server alive interval seconds.

[Back to TOC](#table-of-contents)

#### thread_stack_size 
default: 64KB

Thread stack size, should >= 64KB.

[Back to TOC](#table-of-contents)

#### storage_ip_changed_auto_adjust 
default: true

Auto adjust when the ip address of the storage server changed.

[Back to TOC](#table-of-contents)

#### storage_sync_file_max_delay 
default: 86400s

Storage sync file max delay seconds.

since V2.00

[Back to TOC](#table-of-contents)

#### storage_sync_file_max_time 
default: 300s

The max time of storage sync a file.

since V2.00

[Back to TOC](#table-of-contents)

#### use_trunk_file 
default: false

If use a trunk file to store several small files.

since V3.00

[Back to TOC](#table-of-contents)

#### slot_min_size 
default: 256 bytes

The min slot size, should <= 4KB.

since V3.00

[Back to TOC](#table-of-contents)

#### slot_max_size 
default: 16MB

The max slot size, should > slot_min_size. Store the upload file to trunk file when it's size <=  this value.

since V3.00

[Back to TOC](#table-of-contents)

#### trunk_file_size 
default: 64 MB

The trunk file size, should >= 4MB.

since V3.00

[Back to TOC](#table-of-contents)

#### trunk_create_file_advance 
default: false

If create trunk file advancely.

since V3.06

[Back to TOC](#table-of-contents)

#### trunk_create_file_time_base 
default: 02:00

The time base to create trunk file. The time format: HH:MM.

since V3.06

[Back to TOC](#table-of-contents)

#### trunk_create_file_interval 
default: 38400s

The interval of create trunk file, unit: second.

since V3.06

[Back to TOC](#table-of-contents)

#### trunk_create_file_space_threshold 
default: 0

The threshold to create trunk file when the free trunk file size less than the threshold, will create the trunk files.

since V3.06

[Back to TOC](#table-of-contents)

#### trunk_init_check_occupying 
default: false

If check trunk space occupying when loading trunk free spaces. The occupied spaces will be ignored.

NOTICE: set this parameter to true will slow the loading of trunk spaces when startup. you should set this parameter to true when neccessary.

since V3.09

[Back to TOC](#table-of-contents)

#### trunk_init_reload_from_binlog 
default: false

If ignore storage_trunk.dat, reload from trunk binlog. Set to true once for version upgrade when your version less than V3.10.

since V3.10

[Back to TOC](#table-of-contents)

#### trunk_compress_binlog_min_interval 
default: 0

The min interval for compressing the trunk binlog file. FastDFS compress the trunk binlog when trunk init and trunk destroy recommand to set this parameter to 86400 (one day).

0: never compress

since V5.01

[Back to TOC](#table-of-contents)

#### use_storage_id 
default: false

If use storage ID instead of IP address.

since V4.00

[Back to TOC](#table-of-contents)

#### storage_ids_filename 
default: --

Specify storage ids filename, can use relative or absolute path.

since V4.00

[Back to TOC](#table-of-contents)

#### id_type_in_filename 
default: ip

Id type of the storage server in the filename, values are:
* ip: the ip address of the storage server
* id: the server id of the storage server

This paramter is valid only when use_storage_id set to true.

since V4.03

[Back to TOC](#table-of-contents)

#### store_slave_file_use_link 
default: false

If store slave file use symbol link.

since V4.01

[Back to TOC](#table-of-contents)

#### rotate_error_log 
default: false

If rotate the error log every day.

since V4.02

[Back to TOC](#table-of-contents)

#### error_log_rotate_time
default: 00:00

Rotate error log time base, time format: HH:MM. Hour from 0 to 23, Minute from 0 to 59.

since V4.02

[Back to TOC](#table-of-contents)

#### rotate_error_log_size 
default: 0

Rotate error log when the log file exceeds this size. 0 means never rotates log file by log file size

since V4.02

[Back to TOC](#table-of-contents)

#### log_file_keep_days 
default: 0

Keep days of the log files. 0 means do not delete old log files

[Back to TOC](#table-of-contents)

#### use_connection_pool 
default: false

If use connection pool

since V4.05

[Back to TOC](#table-of-contents)

#### connection_pool_max_idle_time 
default: 3600s

Connections whose the idle time exceeds this time will be closed. (unit: second)

since V4.05

[Back to TOC](#table-of-contents)

#### http.server_port
default: 8080

HTTP port on this tracker server.

[Back to TOC](#table-of-contents)

#### http.check_alive_interval
default: 30s

Check storage HTTP server alive interval seconds. <= 0 for never check.

[Back to TOC](#table-of-contents)

#### http.check_alive_type
default: tcp

Check storage HTTP server alive type, values are:
* tcp : connect to the storge server with HTTP port only, do not request and get response
* http : storage check alive url must return http status 200

[Back to TOC](#table-of-contents)

#### http.check_alive_uri
default: /

Check storage HTTP server alive uri/url.

NOTE: storage embed HTTP server support uri: /status.html

[Back to TOC](#table-of-contents)


## storage
#### disabled
default: false

Is this config file disabled? false for enabled, true for disabled.

[Back to TOC](#table-of-contents)

#### group_name
default: --

The name of the group this storage server belongs to. (must have)

[Back to TOC](#table-of-contents)

#### bind_addr
default: all addresses of this host

Bind an address of this host. Empty for bind all addresses of this host.

[Back to TOC](#table-of-contents)

#### client_bind
default: true

If bind an address of this host when connect to other servers (this storage server as a client)
*   true for binding the address configed by above parameter: "bind_addr"
*   false for binding any address of this host 

[Back to TOC](#table-of-contents)

#### port
default: 23000

The storage server port.

[Back to TOC](#table-of-contents)

#### connect_timeout
default: 30s

Connect timeout in seconds.

[Back to TOC](#table-of-contents)

#### network_timeout
default: 30s

Network timeout in seconds.

[Back to TOC](#table-of-contents)

#### heart_beat_interval
default: 30s

Heart beat interval in seconds.

[Back to TOC](#table-of-contents)

#### stat_report_interval
default: 300s

Disk usage report interval in seconds.

[Back to TOC](#table-of-contents)

#### base_path
default: 

The base path to store data and log files. (must have)

[Back to TOC](#table-of-contents)

#### max_connections
default: 256

Max concurrent connections the server supported. More max_connections means more memory will be used.

[Back to TOC](#table-of-contents)

#### buff_size 
default: 64KB

The buff size to recv / send data, this parameter must more than 8KB.

since V2.00

[Back to TOC](#table-of-contents)

#### accept_threads
default: 1

Accept thread count.

since V4.07

[Back to TOC](#table-of-contents)

#### work_threads
default: 4

Work thread count (should <= max_connections), work thread deal network io.

since V2.00

[Back to TOC](#table-of-contents)

#### disk_rw_separated 
default: true

If disk read / write separated, false for mixed read and write, true for separated read and write.

since V2.00

[Back to TOC](#table-of-contents)

#### disk_reader_threads 
default: 1

Disk reader thread count per store base path. For mixed read / write, this parameter can be 0.

since V2.00

[Back to TOC](#table-of-contents)

#### disk_writer_threads 
default: 

Disk writer thread count per store base path. For mixed read / write, this parameter can be 0.

since V2.00

[Back to TOC](#table-of-contents)

#### sync_wait_msec
default: 200ms

When no entry to sync, try read binlog again after X milliseconds. (must > 0)

[Back to TOC](#table-of-contents)

#### sync_interval
default: 0

After sync a file, usleep milliseconds, 0 for sync successively (never call usleep).

[Back to TOC](#table-of-contents)

#### sync_start_time
default: 00:00

Storage sync start time of a day, time format: HH:MM (Hour from 0 to 23, Minute from 0 to 59).

[Back to TOC](#table-of-contents)

#### sync_end_time
default: 23:59

Storage sync end time of a day, time format: HH:MM (Hour from 0 to 23, Minute from 0 to 59).

[Back to TOC](#table-of-contents)

#### write_mark_file_freq
default: 500

Write to the mark file after sync N files.

[Back to TOC](#table-of-contents)

#### store_path_count
default: 1

Path(disk or mount point) count

[Back to TOC](#table-of-contents)

#### store_path0
default: 

store_path# (based 0), if store_path0 not exists, it's value is base_path. (The paths must be exist)

[Back to TOC](#table-of-contents)

#### subdir_count_per_path
default: 256

Subdir_count  * subdir_count directories will be auto created under each store_path (disk), value can be 1 to 256, default value is 256.

[Back to TOC](#table-of-contents)

#### tracker_server
default: 

Tracker_server can ocur more than once, and tracker_server format is "host:port", host can be hostname or ip address.

[Back to TOC](#table-of-contents)

#### log_level
default: 

Standard log level as syslog, case insensitive, value list:
* emerg for emergency
* alert
* crit for critical
* error
* warn for warning
* notice
* info
* debug

[Back to TOC](#table-of-contents)

#### run_by_group
default: 

Unix group name to run this program,  not set (empty) means run by the group of current user.

[Back to TOC](#table-of-contents)

#### run_by_user
default: 

Unix username to run this program, not set (empty) means run by current user.

[Back to TOC](#table-of-contents)

#### allow_hosts
default: *

Allow_hosts can ocur more than once, host can be hostname or ip address, "*" means match all ip addresses, can use range like this: 10.0.1.[1-15,20] or host[01-08,20-25].domain.com, for example:
* allow_hosts=10.0.1.[1-15,20]
* allow_hosts=host[01-08,20-25].domain.com

[Back to TOC](#table-of-contents)

#### file_distribute_path_mode
default: 0

The mode of the files distributed to the data path.
* 0: round robin (default)
* 1: random, distributted by hash code

[Back to TOC](#table-of-contents)

#### file_distribute_rotate_count
default: 100

Valid when file_distribute_to_path is set to 0 (round robin), when the written file count reaches this number, then rotate to next path. 

[Back to TOC](#table-of-contents)

#### fsync_after_written_bytes
default: 0

Call fsync to disk when write big file.
* 0: never call fsync
* other: call fsync when written bytes >= this bytes

default value is 0 (never call fsync)

[Back to TOC](#table-of-contents)

#### sync_log_buff_interval
default: 10s

Sync log buff to disk every interval seconds. (must > 0)

[Back to TOC](#table-of-contents)

#### sync_binlog_buff_interval
default: 60s

Sync binlog buff / cache to disk every interval seconds.

[Back to TOC](#table-of-contents)

#### sync_stat_file_interval
default: 300s

Sync storage stat info to disk every interval seconds.

[Back to TOC](#table-of-contents)

#### thread_stack_size
default: 512KB

Thread stack size, should >= 512KB.

[Back to TOC](#table-of-contents)

#### upload_priority
default: 10

The priority as a source server for uploading file. The lower this value, the higher its uploading priority.

[Back to TOC](#table-of-contents)

#### if_alias_prefix
default: 

The NIC alias prefix, such as eth in Linux, you can see it by ifconfig -a multi aliases split by comma. Empty value means auto set by OS type.

[Back to TOC](#table-of-contents)

#### check_file_duplicate
default: 0

If check file duplicate, when set to true, use FastDHT to store file indexes.
* 1 or yes: need check
* 0 or no: do not check

[Back to TOC](#table-of-contents)

#### file_signature_method
default: hash

File signature method for check file duplicate,
* hash: four 32 bits hash code
* md5: MD5 signature

since V4.01

[Back to TOC](#table-of-contents)

#### key_namespace
default: 

Namespace for storing file indexes (key-value pairs), this item must be set when check_file_duplicate is true / on.

[Back to TOC](#table-of-contents)

#### keep_alive
default: 0

Set keep_alive to 1 to enable persistent connection with FastDHT servers.

[Back to TOC](#table-of-contents)

#### use_access_log 
default: false

If log to access log.

since V4.00

[Back to TOC](#table-of-contents)

#### rotate_access_log 
default: false

If rotate the access log every day.

since V4.00

[Back to TOC](#table-of-contents)

#### access_log_rotate_time
default: 00:00

Rotate access log time base, time format: HH:MM, Hour from 0 to 23, Minute from 0 to 59.

since V4.00

[Back to TOC](#table-of-contents)

#### rotate_error_log 
default: false

If rotate the error log every day.

since V4.02

[Back to TOC](#table-of-contents)

#### error_log_rotate_time
default: 00:00

Rotate error log time base, time format: HH:MM, Hour from 0 to 23, Minute from 0 to 59.

since V4.02

[Back to TOC](#table-of-contents)

#### rotate_access_log_size 
default: 0

Rotate access log when the log file exceeds this size, 0 means never rotates log file by log file size.

since V4.02

[Back to TOC](#table-of-contents)

#### rotate_error_log_size 
default: 0

Rotate error log when the log file exceeds this size, 0 means never rotates log file by log file size.

since V4.02

[Back to TOC](#table-of-contents)

#### log_file_keep_days 
default: 0

Keep days of the log files, 0 means do not delete old log files.

[Back to TOC](#table-of-contents)

#### file_sync_skip_invalid_record
default: false

If skip the invalid record when sync file.

since V4.02

[Back to TOC](#table-of-contents)

#### use_connection_pool 
default: false

If use connection pool.

since V4.05

[Back to TOC](#table-of-contents)

#### connection_pool_max_idle_time 
default: 3600s

Connections whose the idle time exceeds this time will be closed. (unit: second)

since V4.05

[Back to TOC](#table-of-contents)

#### http.domain_name
default: 

Use the ip address of this storage server if domain_name is empty, else this domain name will ocur in the url redirected by the tracker server.

[Back to TOC](#table-of-contents)

#### http.server_port
default: 8888

The port of the web server on this storage server.

[Back to TOC](#table-of-contents)

## client

To be continue.
