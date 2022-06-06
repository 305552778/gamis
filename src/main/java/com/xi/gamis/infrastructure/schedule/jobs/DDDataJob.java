package com.xi.gamis.infrastructure.schedule.jobs;

import org.quartz.*;

import java.util.Date;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class DDDataJob implements Job {
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDetailMap= jobExecutionContext.getJobDetail().getJobDataMap();
        JobDataMap triggerDetailMap= jobExecutionContext.getTrigger().getJobDataMap();
        JobDataMap xx=jobExecutionContext.getMergedJobDataMap();
        System.out.println(url+"简单任务开始执行:"+new Date());
        //System.out.println(triggerDetailMap.getString("trigger_p1")+"简单任务开始执行:"+new Date());

    }
}
