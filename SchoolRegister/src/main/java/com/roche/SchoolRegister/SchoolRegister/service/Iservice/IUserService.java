package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User save(User user);

    List<User> findAll();

    Optional<User> findByUsername(String username);

    String findRoleByUsername(String username);

    void delete(User user);

    List<User> findByQuery(HttpServletRequest request);

}
