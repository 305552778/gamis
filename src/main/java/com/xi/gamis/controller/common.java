package com.xi.gamis.controller;

import com.xi.gamis.entity.SysJllb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("common")
public class common {
    @RequestMapping("/failure")
    public String lifeView(Model model)
    {
        return "failure";
    }
}
