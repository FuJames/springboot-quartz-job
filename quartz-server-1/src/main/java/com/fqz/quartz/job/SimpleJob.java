package com.fqz.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author fuqianzhong
 * @date 19/1/15
 */
@Component
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("<<<<<< I am a simple job at "+System.currentTimeMillis()+">>>>>>");
    }
}
