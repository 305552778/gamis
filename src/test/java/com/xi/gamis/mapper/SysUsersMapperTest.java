package com.xi.gamis.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xi.gamis.entity.SysUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SysUsersMapperTest {
    @Autowired
    public SysUsersMapper sysUsersMapper;
    @BeforeEach
    void setUp() {
        //sysUsersMapper=new SysUsersMapper() ;

    }

    @Test
    void selectById() {
        //List<Map<String,Object>> UserVo=sysUsersMapper.getUserVO("离职");
        SysUsers s= sysUsersMapper.selectById(2);
        System.out.println(s);


    }
}