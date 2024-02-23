package com.roche.SchoolRegister.SchoolRegister.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }

}
