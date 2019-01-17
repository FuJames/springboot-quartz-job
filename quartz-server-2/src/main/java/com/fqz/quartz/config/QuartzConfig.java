package com.fqz.quartz.config;

import com.fqz.quartz.scheduler.DurableScheduler;
import org.quartz.Scheduler;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author fuqianzhong
 * @date 19/1/15
 */
@Configuration
@ComponentScan("com.fqz.quartz")
public class QuartzConfig {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) throws IOException {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setAutoStartup(true);
//        schedulerFactory.setStartupDelay(20);
        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

        return schedulerFactory;
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public DurableScheduler durableScheduler(SchedulerFactoryBean schedulerFactoryBean){
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        return new DurableScheduler(scheduler);
    }
}
