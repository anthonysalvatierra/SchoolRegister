package com.roche.SchoolRegister.SchoolRegister.dao.Idao;

import com.roche.SchoolRegister.SchoolRegister.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserDao {

    String findAll = "SELECT u FROM User u";
    String findByUsername = "SELECT u FROM User u WHERE username = :username";
    String findRoleByUsername = "SELECT u.role FROM User u WHERE username = :username";

    List<?> findAll();

    Optional<User> findByUsername(String username);

    String findRoleByUsername(String username);

}
