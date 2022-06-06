package com.xi.gamis;

import com.xi.gamis.infrastructure.schedule.jobs.DDDataJob;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.mybatis.spring.annotation.MapperScan;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.activiti.spring.boot.SecurityAutoConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
})*/

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)//开启SpringSecurity @Secured、@PreAuthorize注解
public class GamisApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GamisApplication.class, args);
        //ProcessEngine pe= ProcessEngines.getDefaultProcessEngine();
        //部署

//        RepositoryService rs=pe.getRepositoryService();
//        Deployment dp=rs.createDeployment().addClasspathResource("processes/aaa.bpmn").name("资产调拨").deploy();
//        System.out.println("流程部署ID："+dp.getId());
//        System.out.println("流程部署名称："+dp.getName());

        //启动
        //设置assignee
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("uid","邹涛");
//        variables.put("drsp","龚强");
//        variables.put("dcsp","缪丁");
//        RuntimeService rs=pe.getRuntimeService();
//        ProcessInstance pi= rs.startProcessInstanceByKey("assetTransfer0",variables);//可在此添加流程变量
//        System.out.println("流程定义ID："+pi.getProcessDefinitionId());
//        System.out.println("实例ID："+pi.getId());
//        System.out.println("当前活动ID："+pi.getActivityId());

        //执行当前任务
//        Map<String, Object> days=new HashMap<String, Object>();
//        days.put("days",4);
//        TaskService ts=pe.getTaskService();
//        Task tsk=ts.createTaskQuery().processDefinitionKey("holidayApplication").taskAssignee("cw").singleResult();
//        ts.complete(tsk.getId(),days);//可在此添加流程变量

        //查询历史任务


        //BusinessKey
        /*RuntimeService rs=pe.getRuntimeService();
        ProcessInstance pi= rs.startProcessInstanceByKey("holidayApplication","BusinessKey");
        */
        //查询当前任务

        //String assignee="employee1";
        //TaskService ts=pe.getTaskService();
        //List<Task> tasks=ts.createTaskQuery().processDefinitionKey("holidayApplication").taskAssignee(assignee).list();
//        List<Task> tasks=pe.getTaskService().createTaskQuery().processDefinitionKey("assetTransfer0").list();
//        for(Task _tsk:tasks)
//        {
//            System.out.println("实例ID："+_tsk.getProcessInstanceId());
//            System.out.println("任务ID："+_tsk.getId());
//            System.out.println("任务负责人："+_tsk.getAssignee());
//            System.out.println("表单Key："+_tsk.getFormKey());
//
//        }



    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
     {
       return builder.sources(GamisApplication.class);
     }
}
