package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<?> findAll();

    Optional<User> findByUsername(String username);

    String findRoleByUsername(String username);

}
