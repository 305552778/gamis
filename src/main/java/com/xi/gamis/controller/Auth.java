package com.xi.gamis.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

@Controller
@RequestMapping("auth")
public class Auth {
    @RequestMapping("current")
    public String GetCurrentUser()
    {
       String userName= SecurityContextHolder.getContext().getAuthentication().getName();
       return userName;
    }
    @RequestMapping("login")
    public String login(Model model)
    {
       return "authen";
    }
}
