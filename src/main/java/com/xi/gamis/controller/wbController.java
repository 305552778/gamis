package com.xi.gamis.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xi.gamis.entity.WbLog;
import com.xi.gamis.service.impl.WbLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/wb")
public class wbController {
    @Autowired
    private WbLogServiceImpl wb_service;
    @GetMapping("/my")
    public ModelAndView GetMyLog()
    {
        ModelAndView model = new ModelAndView("assetsLife");
        //model.addObject("userList",userservice.listUser());
        return model;
        //QueryWrapper<WbLog> qw=new QueryWrapper<WbLog>();
        //qw.gt("writer_id",uId);
         //List<WbLog> x= wb_service.list(qw);
        //model.addAllAttributes(x);
       // return "assetsLife";

    }

}
