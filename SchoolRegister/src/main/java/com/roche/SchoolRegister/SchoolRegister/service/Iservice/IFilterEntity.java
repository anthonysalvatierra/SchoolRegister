package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface IFilterEntity {

    List<Person> filterEntity(String entity, HttpServletRequest request);

    List<Student> filterStudent(HttpServletRequest request, String entity);

    List<Teacher> filterTeacher(HttpServletRequest request);

    List<Admin> filterAdmin(HttpServletRequest request);

    List<Career> filterCareer(HttpServletRequest request);

    List<User> filterUser(HttpServletRequest request);

    List<Person> defaultValue(String entity);



}
