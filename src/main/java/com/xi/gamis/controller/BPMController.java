package com.xi.gamis.controller;

import com.xi.gamis.dto.CommonResult;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("bpm")
public class BPMController {
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;

    @ResponseBody
    @RequestMapping("deploy")
    public CommonResult deploy(String url,String processName)
    {
        //http://localhost:84/bpm/deploy?url=processes/aaa.bpmn&processName=%E6%B5%8B%E8%AF%95%E8%B0%83%E6%8B%A8%E5%AE%A1%E6%89%B9
        Deployment dp=repositoryService.createDeployment().addClasspathResource(url).name(processName).deploy();
        return new CommonResult(1,dp.getKey(),"success");
    }

    @ResponseBody
    @RequestMapping("start")
    public CommonResult startProcessInstance(String processDefinitionKey, String businessKey, String instanceName,Map<String, Object> variables)
    {
        Map<String, Object> x=new HashMap<>();
            x.put("uid","邹涛");
            //x.put("drsp","龚强");
            x.put("candidate_drsp","张三,李四");
            x.put("dcsp","缪丁");
            x.put("adminIn","增辉");
            x.put("adminOut","小刘");
            x.put("sameCampus",1);
        ProcessInstance pi= runtimeService.startProcessInstanceByKey(processDefinitionKey,businessKey,x);//可在此添加流程变量
        runtimeService.setProcessInstanceName(pi.getProcessInstanceId(),instanceName);
        return new CommonResult(1,pi.getId(),"success");
    }

   @ResponseBody
   @RequestMapping("excute_a")
   public CommonResult excute(String taskId,String userId,String comment,@Nullable HashMap<String, Object> variables)
    {
        //执行当前任务
        //Task tsk=taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskAssignee(userId).singleResult();
        Task tsk=taskService.createTaskQuery().taskId(taskId).taskAssignee(userId).singleResult();
        if(tsk!=null)
        {
            if(!StringUtils.isAllEmpty(comment))
            {
                taskService.addComment(taskId, tsk.getProcessInstanceId(), comment);  // 给任务添加批注
            }
            Map<String, Object> x=new HashMap<>();
            //x.put("candidate_drsp","张三,李四");
            x.put("yld","龚强");
            taskService.complete(taskId,x);//可在此添加流程变量
            return new CommonResult(1,"","success");
        }
        else
        {
            return new CommonResult(0,"","no task");
        }

    }
    @ResponseBody
    @RequestMapping("excute_c")
    public CommonResult claimAndExcute(String taskId,String userId,String comment,@Nullable @RequestBody Map<String, Object> variables)
    {
        Task tsk=taskService.createTaskQuery().taskId(taskId).taskCandidateOrAssigned(userId).singleResult();
        if(tsk!=null)
        {
            if(!StringUtils.isAllEmpty(comment))
            {
                taskService.addComment(taskId, tsk.getProcessInstanceId(), comment);  // 给任务添加批注
            }

            taskService.claim(taskId,userId);
            taskService.complete(taskId,variables);//可在此添加流程变量
            return new CommonResult(1,"","success");
        }
        else
        {
            return new CommonResult(0,"","no task");
        }

    }
    @ResponseBody
    @RequestMapping("ins")
    public CommonResult getProcessInstance(String processDefinitionKey,@Nullable String insId)
    {
        ProcessInstanceQuery piq= runtimeService.createProcessInstanceQuery().processDefinitionKey(processDefinitionKey);
        if(!StringUtils.isAllEmpty(insId))
        {
            piq.processInstanceId(insId);
        }

        List<Map<String,Object>> r=new ArrayList<>();
        List<ProcessInstance> instances=piq.list();
        for(ProcessInstance _ins:instances)
        {
            Map<String,Object> item=new HashMap<>();
            item.put("instanceId",_ins.getId());
            item.put("startUserId",_ins.getStartUserId());
            item.put("name",_ins.getName());
            item.put("description",_ins.getDescription());
            item.put("startTime",_ins.getStartTime());
            item.put("variables",_ins.getProcessVariables());
            item.put("isEnded",_ins.isEnded());
            item.put("status",taskService.createTaskQuery().processInstanceId(_ins.getId()).list());
            r.add(item);
        }
        return new CommonResult(1,r,"success");

    }

    @ResponseBody
    @RequestMapping("tasks")
    public CommonResult getTasks(String processDefinitionKey,@Nullable String candidate,@Nullable String assignee)
    {
        TaskQuery tq=taskService.createTaskQuery().processDefinitionKey(processDefinitionKey);
        if(!StringUtils.isAllEmpty(assignee))
        {
            tq.taskAssignee(assignee);
        }
        else
        {
            if(!StringUtils.isAllEmpty(candidate))
            {
                tq.taskCandidateOrAssigned(candidate);
            }

        }
        List<Map<String,Object>> r=new ArrayList<>();
        List<Task> tasks=tq.list();//不能直接返回
        for(Task _tsk:tasks)
        {
            Map<String,Object> item=new HashMap<>();
            item.put("instanceId",_tsk.getProcessInstanceId());
            item.put("taskId",_tsk.getId());
            item.put("assignee",_tsk.getAssignee());
            item.put("name",_tsk.getName());
            item.put("description",_tsk.getDescription());
            item.put("dueDate",_tsk.getDueDate());
            item.put("variables",taskService.getVariables(_tsk.getId()));
            item.put("formKey",_tsk.getFormKey());
            r.add(item);

        }
        return new CommonResult(1,r,"success");
    }




}
