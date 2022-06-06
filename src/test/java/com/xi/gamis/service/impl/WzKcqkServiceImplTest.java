package com.xi.gamis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xi.gamis.entity.SysJllb;
import com.xi.gamis.entity.SysUsers;
import com.xi.gamis.entity.WzKcqk;
import lombok.var;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WzKcqkServiceImplTest {

    @Autowired
    SysUsersServiceImpl usersService;
    @Autowired
    SysJllbServiceImpl sysJllbService;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStock() {
        ProcessEngine pe= ProcessEngines.getDefaultProcessEngine();
        RepositoryService rs=pe.getRepositoryService();
        Deployment dp=rs.createDeployment().addClasspathResource("processes/testActi.bpmn").name("测试请假流程").deploy();
        System.out.println("流程部署ID："+dp.getId());
        System.out.println("流程部署名称："+dp.getName());
        /*String jllb=",1,16,17,18,21,";
        String [] x=jllb.split(",");
        List ff=new  ArrayList();
        for (int i = 0; i < x.length; i++) {
            ff.add(x[i]);
        }
            List<SysJllb> jls= sysJllbService.listByIds(Arrays.asList(1,2,3));
        System.out.println(jls.toString());*/
    }
}