package com.xi.gamis.infrastructure.schedule.jobs;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.Map;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class HTTPJob extends QuartzJobBean {
    private String url;
    private String contentType;
    private Map<String,String> parameters;
    private int timeOut;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap para=jobExecutionContext.getMergedJobDataMap();
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("quartz集群任务任务开始执行:"+new Date());
        System.out.println("taskname:"+jobExecutionContext.getJobDetail().getKey().getName());
        System.out.println("trigger key:"+jobExecutionContext.getTrigger().getKey().getName()+"job key:"+jobExecutionContext.getJobDetail().getKey().getName());
        System.out.println("运行参数url："+para.getString("url"));
        System.out.println("运行参数contentType："+para.getString("contentType"));
        System.out.println("下次运行时间："+jobExecutionContext.getNextFireTime());
        System.out.println("---------------------------------------------------------------------------");

    }
}
