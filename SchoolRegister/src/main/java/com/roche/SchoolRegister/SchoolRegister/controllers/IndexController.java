package com.roche.SchoolRegister.SchoolRegister.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return "dashboard";
    }

}
