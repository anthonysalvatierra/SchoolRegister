package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherService {

    List<Teacher> findAll();

    Teacher save(Teacher teacher);

    void delete(Teacher teacher);

    Optional<Teacher> findById(Long id);

    Teacher findByDna(String dna);

    List<Teacher> findByName(String name);

}
