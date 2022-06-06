package com.xi.gamis.service.impl;

import com.xi.gamis.GamisApplication;
import com.xi.gamis.entity.SysUsers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = GamisApplication.class)
class SysBmlbServiceImplTest {

    @Autowired
    SysBmlbServiceImpl svr;
    @Test
    void getLeader() {
       SysUsers u= svr.GetLeader(148);
        System.out.println(u.getXm());
    }
}