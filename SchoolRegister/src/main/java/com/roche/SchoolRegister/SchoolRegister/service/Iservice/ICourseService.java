package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Course;
import com.roche.SchoolRegister.SchoolRegister.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    Course save(Course course);

    List<Course> findAll();

    Optional<Course> findById(Long id);

    List<Course> findByTeacher(Teacher teacher);

    List<Course> findCourseWhereTeacherIsNotNull();

}
