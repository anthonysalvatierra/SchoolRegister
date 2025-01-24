package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.ICourseDao;
import com.roche.SchoolRegister.SchoolRegister.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl {

    @Autowired
    private ICourseDao courseDao;

    public Course save(Course course){
        return this.courseDao.save(course);
    }

}
