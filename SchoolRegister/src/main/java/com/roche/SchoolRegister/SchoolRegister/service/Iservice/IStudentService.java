package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Student;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

public interface IStudentService {

    Student save(Student student);

    List<Student> findAll();

    void delete(Student student);

    Optional<Student> findById(Long id);

    Student findByDna(String dna);

    List<Student> findByQuery(HttpServletRequest request);

}
