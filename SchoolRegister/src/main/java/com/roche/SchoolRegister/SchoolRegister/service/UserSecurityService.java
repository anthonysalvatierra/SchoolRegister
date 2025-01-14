package com.roche.SchoolRegister.SchoolRegister.service;

import com.roche.SchoolRegister.SchoolRegister.entities.User;
import com.roche.SchoolRegister.SchoolRegister.errors.ErrorController;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    private final Logger log = LogManager.getLogger(this);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user: " + username + " not found"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(username)
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

}
