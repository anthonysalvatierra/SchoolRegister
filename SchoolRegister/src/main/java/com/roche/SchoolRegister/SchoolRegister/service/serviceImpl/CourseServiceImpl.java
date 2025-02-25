package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.dao.daoImpl.CourseDaoImpl;
import com.roche.SchoolRegister.SchoolRegister.entities.Course;
import com.roche.SchoolRegister.SchoolRegister.entities.Teacher;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ICourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CourseServiceImpl implements ICourseService {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private CourseDaoImpl courseDao;

    @Override
    public Course save(Course course) {
        Course courseInserted = new Course();

        try{
            courseInserted = this.courseDao.save(course);
        } catch (Exception exception) {
            log.error(MessageConstant.ERROR_PERSIST_ENTITY.name().concat(" ")
                    .concat(exception.getMessage()));
        }
        return courseInserted;
    }

    @Override
    public List<Course> findAll() {
        return this.courseDao.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return this.courseDao.findById(id);
    }

    @Override
    public List<Course> findByTeacher(Teacher teacher) {
        return  this.courseDao.findByTeacher(teacher);
    }

    @Override
    public List<Course> findCourseWhereTeacherIsNotNull() {
        return this.courseDao.findCourseWhereTeacherIsNotNull();
    }
}
