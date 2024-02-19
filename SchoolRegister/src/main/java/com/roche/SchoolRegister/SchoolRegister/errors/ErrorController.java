package com.roche.SchoolRegister.SchoolRegister.errors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    private final Logger log = LogManager.getLogger(ErrorController.class);

    @ExceptionHandler(UsernameNotFoundException.class)
    public String userNotFound(){
        return "redirect:/";
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public String internalAuth(){
        return "redirect:/";
    }

}
