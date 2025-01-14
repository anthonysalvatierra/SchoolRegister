package com.roche.SchoolRegister.SchoolRegister.controllers;

import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/dashboard")
    public String dashboard(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = this.userService.findRoleByUsername(auth.getName());
        return role.toLowerCase() + "/dashboard";
    }

}
