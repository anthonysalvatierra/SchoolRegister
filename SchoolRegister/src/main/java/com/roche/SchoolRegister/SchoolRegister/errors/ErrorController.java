package com.roche.SchoolRegister.SchoolRegister.errors;

import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ILoadableService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    private final Logger log = LogManager.getLogger(ErrorController.class);

    @Autowired
    private ILoadableService loadableService;

    @ExceptionHandler(Exception.class)
    public String userNotFound(Model model){
        return "index";
    }

}
