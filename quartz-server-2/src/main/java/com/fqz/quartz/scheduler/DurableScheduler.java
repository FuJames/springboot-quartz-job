package com.fqz.quartz.scheduler;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 * @author fuqianzhong
 * @date 19/1/15
 */
public class DurableScheduler {
    private Scheduler scheduler;

    public DurableScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void init() throws SchedulerException {
        scheduler.start();
    }

    public void destroy() throws SchedulerException {
        if(scheduler != null){
            scheduler.shutdown();
        }
    }

    public Scheduler getScheduler() {
        return scheduler;
    }
}
