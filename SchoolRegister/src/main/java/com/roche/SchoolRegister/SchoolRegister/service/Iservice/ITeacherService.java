package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Teacher;

import java.util.List;

public interface ITeacherService {

    List<Teacher> findAll();

    Teacher save(Teacher teacher);

}
