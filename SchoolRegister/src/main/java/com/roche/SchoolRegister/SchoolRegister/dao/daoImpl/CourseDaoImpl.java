package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.dao.Idao.ICourseDao;
import com.roche.SchoolRegister.SchoolRegister.entities.Course;
import com.roche.SchoolRegister.SchoolRegister.entities.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDaoImpl {

    @Autowired
    private ICourseDao courseDao;

    @PersistenceContext
    private EntityManager em;

    private final Logger log = LogManager.getLogger(this);

    public Course save(Course course){
        return this.courseDao.save(course);
    }

    public Optional<Course> findById(Long id){
        return this.courseDao.findById(id);
    }

    public List<Course> findAll(){
        return (List<Course>) this.courseDao.findAll();
    }

    public List<Course> findByTeacher(Teacher teacher){

        List course = new ArrayList();

        try{
            course = this.em.createQuery(ICourseDao.findByTeacher)
                    .setParameter("teacher", teacher)
                    .getResultList();
        }catch (Exception exception){
            log.info(MessageConstant.ERROR.name().concat(" ").concat(exception.getMessage()));
            return course;
        }

        log.info(MessageConstant.COURSE.name().concat(" ").concat(course.toString()));
        return course;
    }

    public List<Course> findCourseWhereTeacherIsNotNull(){
        return this.em.createQuery(ICourseDao.findCourseWhereTeacherIsNotNull).getResultList();
    }

}
