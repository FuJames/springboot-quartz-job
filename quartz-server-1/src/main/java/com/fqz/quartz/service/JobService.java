package com.fqz.quartz.service;

import com.fqz.quartz.job.SimpleJob;
import com.fqz.quartz.scheduler.DurableScheduler;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author fuqianzhong
 * @date 19/1/15
 * Quartz调度器
 * 1. Scheduler,JobDetail,Trigger 完成一次调度
 * 2. name/group: JobDetail和Trigger都有;group.name唯一,如果重复定义时,会抛出exception
 * 3. 一个JobDetail对象,同时只能被一个Scheduler对象调用,否则会抛出exception
 */
@Service
public class JobService {
    @Autowired
    private DurableScheduler durableScheduler;

    private Logger logger = LoggerFactory.getLogger(JobService.class);

    public void triggerSimple(){
        JobDetail jobSimple = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job_name_simple","job_group_simple")
                .usingJobData("name","simple_job")
                .build();
        Date startTime = DateBuilder.nextGivenSecondDate(new Date( ),3);
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger_name_simple", "trigger_group_simple")
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .withRepeatCount(5))
                .build();

        try {
            durableScheduler.getScheduler().scheduleJob(jobSimple, simpleTrigger);
        } catch (SchedulerException e) {
            logger.error("",e);
        }
    }

    public void triggerCron(){
        //定义JobDetail,任务执行的具体逻辑
        JobDetail jobCron = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("job_name_cron","group_name_cron")
                .usingJobData("name","cron_job")
                .build();

        CronTrigger cronTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger_name_cron", "trigger_group_cron")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
                .build();

        try {
            durableScheduler.getScheduler().scheduleJob(jobCron, cronTrigger);
        } catch (SchedulerException e) {
            logger.error("",e);
        }
    }
}
