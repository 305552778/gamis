package com.xi.gamis.application;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void login() {
        ProcessEngine pe= ProcessEngines.getDefaultProcessEngine();
        RepositoryService rs=pe.getRepositoryService();
        Deployment dp=rs.createDeployment().addClasspathResource("processes/testActi.bpmn").name("测试请假流程").deploy();
        System.out.println("流程部署ID："+dp.getId());
        System.out.println("流程部署名称："+dp.getName());
    }
}