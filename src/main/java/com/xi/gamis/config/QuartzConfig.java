package com.xi.gamis.config;

import com.xi.gamis.infrastructure.schedule.QuartzJobFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.RecursiveTask;


@Configuration
public class QuartzConfig {

    @Autowired
    private DataSource dataSource;
   /* @Bean
    public Scheduler scheduler(QuartzJobFactory quartzJobFactory ) throws Exception {
        SchedulerFactoryBean schedulerFactoryBean=new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(quartzJobFactory);
        schedulerFactoryBean.afterPropertiesSet();
        Scheduler scheduler=schedulerFactoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }*/
    @Bean
    public Scheduler scheduler() throws IOException {
       return   SchedulerfactoryBean().getScheduler();
    }
    @Bean
    public SchedulerFactoryBean SchedulerfactoryBean() throws IOException {
        SchedulerFactoryBean factory=new SchedulerFactoryBean();
        factory.setSchedulerName("cluster_scheduler");
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setApplicationContextSchedulerContextKey("applicationContext");
        factory.setQuartzProperties(quartzProperties());


        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors());
        factory.setTaskExecutor(executor);
        //factory.setTaskExecutor(schedulerThreadPool());

        factory.setStartupDelay(10);
        factory.setAutoStartup(true);
        factory.setConfigLocation(new ClassPathResource("/quartz.properties"));
        return factory;

    }
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean=new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
    /*@Bean("quartzSchedulerThreadPool")
    public Executor schedulerThreadPool(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors());
        return executor;
    }*/
}
