package com.roche.SchoolRegister.SchoolRegister.dao.Idao;

import com.roche.SchoolRegister.SchoolRegister.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Long> {

    String findByUsername = "SELECT u FROM User u WHERE username = :username";
    String findRoleByUsername = "SELECT u.role FROM User u WHERE username = :username";

}
