package com.xi.gamis.infrastructure.schedule.listener;

import com.xi.gamis.infrastructure.schedule.jobs.HTTPJob;
import com.xi.gamis.infrastructure.schedule.jobs.testJob;
import lombok.SneakyThrows;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/*@Component
public class StartAplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private  Scheduler scheduler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        TriggerKey triggerKey=TriggerKey.triggerKey("http_trigger1","http_group1");
        Trigger trigger= null;
        try {
            trigger = scheduler.getTrigger(triggerKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        if(trigger==null)
        {
            trigger= TriggerBuilder
                    .newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                    .startNow()
                    .build();
            JobDetail jobDetail=JobBuilder.newJob(HTTPJob.class)
                    .withIdentity("http_job1","http_group1")
                    .usingJobData("url","www.gingkoc.edu.cn")
                    .usingJobData("contentType","text/json")
                    .build();
            try {
                scheduler.scheduleJob(jobDetail,trigger);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
            try {
                scheduler.start();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }

        }
    }
}*/
