package com.xi.gamis.infrastructure.schedule.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class testJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("测试集群任务任务开始执行:"+new Date());
        System.out.println("taskname:"+context.getJobDetail().getKey().getName());

    }
}
