package com.roche.SchoolRegister.SchoolRegister.dao.Idao;

import com.roche.SchoolRegister.SchoolRegister.entities.Course;
import org.springframework.data.repository.CrudRepository;

public interface ICourseDao extends CrudRepository<Course, Long> {

    String findByTeacher = "SELECT c FROM Course c WHERE teacher = :teacher";
}
