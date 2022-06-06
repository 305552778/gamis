package com.xi.gamis.controller;

import com.xi.gamis.entity.SysJllb;
import com.xi.gamis.service.impl.SysJllbServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("view")
public class view {
    @Autowired
    private SysJllbServiceImpl sysJllbService;
    //@Secured("ROLE_boss")
    @PreAuthorize("hasAnyAuthority('p341e')")
    @RequestMapping("/life")
    public String lifeView(Model model)
    {
        User xx= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SysJllb> jl=sysJllbService.list();
        model.addAttribute("jl",jl);
        model.addAttribute("user",xx.getUsername());
        return "assetsLife";
    }
    @PreAuthorize("hasAnyAuthority('p341e')")
    @RequestMapping("/index")
    public String index(Model model)
    {
        return "index";
    }
}
