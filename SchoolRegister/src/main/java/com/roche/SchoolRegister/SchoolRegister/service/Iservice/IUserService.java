package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Person;
import com.roche.SchoolRegister.SchoolRegister.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User save(User user);

    List<User> findAll();

    Optional<User> findByUsername(String username);

    String findRoleByUsername(String username);

    void constructUser(Person person);

    void delete(User user);

}
