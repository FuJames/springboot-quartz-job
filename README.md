1. quartz核心模块:
    a.scheduler:负责任务的存储和调度,分为内存存储和持久化存储;内存存储优点:1.架构简单无需依赖db,2.内存操作性能更佳;内存存储缺点:1.数据无持久化,服务重启后任务会丢失,2.内存容量有限,无法支持大数量级的任务;

    b.trigger:任务的触发条件,比如cron表达式、周期性触发等

    c.job:任务的具体执行逻辑

2. quartz调度:
    a. trigger表存储触发条件,默认状态为waiting
    b. scheduler线程轮询trigger表,获取可用触发条件,一个trigger只会对应一个scheduler
    c. scheduler线程查询触发条件对应的job
    d. 选取空闲worker线程执行job

3. quartz集群:
    a. 任务重复执行:

    不同server间竞争job,请求线程先查询waiting状态下的触发条件;遍历触发条件,更新trigger状态为acquire;更新成功的线程获得任务;
    这里利用mysql排它锁,使用update trigger set status = 'acquired' where status = 'waiting' and trigger_group = 'xx' and trigger_name = 'xx',同一时间内只有一个线程可以更新成功,若更新结果<=0,则获取失败;否则,获取成功

    b. server集群伸缩:
        1) 执行scheduler的destroy方法,将server调度的任务改成waiting状态(kill命令时,jvm会直接shut down,不会执行destroy方法,需要引入心跳机制)
        2) clusterManager维护server的心跳,定时更新数据库信息来保持心跳,如果server宕机了,则由其他server删除心跳信息,并则将这个server对应的trigger状态改成waiting
        FQZ_SCHEDULER_STATE : server 心跳状态表
        FQZ_TRIGGERS : trigger 状态表
        FQZ_JOB_DETAILS : Job表

        思考:
        1. server需要遍历所有的trigger,可以让server值遍历trigger_id所hash的值
        2.
