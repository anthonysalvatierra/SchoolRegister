package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Course;
import com.roche.SchoolRegister.SchoolRegister.entities.Teacher;

public interface ICourseService {

    Course save(Course course);

    Course findByTeacher(Teacher teacher);

}
