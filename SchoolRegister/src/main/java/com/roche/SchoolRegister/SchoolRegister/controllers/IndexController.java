package com.roche.SchoolRegister.SchoolRegister.controllers;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.entities.*;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ILoadableService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ILoadableService loadableService;

    private final Logger log = LogManager.getLogger(this);

    @RequestMapping("/dashboard")
    public String dashboard(
            Student student, Teacher teacher, Admin admin,
            Career career, Course course, Model model
    ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = this.userService.findRoleByUsername(auth.getName());

        model.addAttribute(MessageConstant.ENTITIES.name().toLowerCase(), this.loadableService.loadEntities());
        return role.toLowerCase() + "/dashboard";
    }

}
