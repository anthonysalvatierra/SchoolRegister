package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;
import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.entities.*;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteServiceImpl implements IDeleteService {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private ICareerService careerService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Override
    public Boolean deleteEntity(Long id, String filter) {

        Boolean wasDeleted;

        wasDeleted = switch (filter){
            case "students" -> deleteStudent(id);
            case "teachers" -> deleteTeacher(id);
            case "careers" -> deleteCareer(id);
            case "admins" -> deleteAdmin(id);
            default -> defaultDelete(filter);
        };

        return wasDeleted;
    }

    @Override
    public Boolean deleteStudent(Long id) {

        boolean wasDeleted = false;
        Optional<Student> student = this.studentService.findById(id);

        if(student.isEmpty()){
            log.info(MessageConstant.INVALID_ID.name().concat(" ").concat(id.toString()));
            return wasDeleted;
        }

        Optional<User> user = this.userService.findByUsername(student.get().getDna());
        log.info(MessageConstant.USER_DELETED.name().concat(" ".concat(user.toString())));
        log.info(user);
        user.ifPresent(value -> this.userService.delete(value));

        this.studentService.delete(student.get());
        log.info(MessageConstant.ENTITY_DELETED.name().concat(" ".concat(student.toString())));
        wasDeleted = true;

        return wasDeleted;
    }

    @Override
    public Boolean deleteTeacher(Long id) {
        boolean wasDeleted = false;
        Optional<Teacher> teacher = this.teacherService.findById(id);

        if(teacher.isEmpty()){
            log.info(MessageConstant.INVALID_ID.name().concat(" ".concat(id.toString())));
            return wasDeleted;
        }

        Course course = this.courseService.findByTeacher(teacher.get());

        if(course != null){
            log.info(MessageConstant.COURSE.name().concat(" ").concat(course.toString()));
            course.setTeacher(null);
            this.courseService.save(course);
        }

        Optional<User> user = this.userService.findByUsername(teacher.get().getDna());
        log.info(MessageConstant.USER_DELETED.name().concat(" ".concat(user.toString())));
        user.ifPresent(value -> this.userService.delete(value));

        this.teacherService.delete(teacher.get());
        log.info(MessageConstant.ENTITY_DELETED.name().concat(" ".concat(teacher.toString())));
        wasDeleted = true;

        return wasDeleted;
    }

    @Override
    public Boolean deleteCareer(Long id) {
        boolean wasDeleted = false;
        Optional<Career> career = this.careerService.findById(id);

        if(career.isEmpty()){
            log.info(MessageConstant.INVALID_ID.name().concat(" ".concat(id.toString())));
            return wasDeleted;
        }

        this.careerService.delete(career.get());
        log.info(MessageConstant.ENTITY_DELETED.name().concat("".concat(career.toString())));
        wasDeleted = true;

        return wasDeleted;
    }

    @Override
    public Boolean deleteAdmin(Long id) {
        boolean wasDeleted = false;
        Optional<Admin> admin = this.adminService.findById(id);

        if(admin.isEmpty()){
            log.info(MessageConstant.INVALID_ID.name().concat(" ".concat(id.toString())));
            return wasDeleted;
        }

        Optional<User> user = this.userService.findByUsername(admin.get().getDna());
        log.info(MessageConstant.USER_DELETED.name().concat(" ".concat(user.toString())));
        user.ifPresent(value -> this.userService.delete(value));

        this.adminService.delete(admin.get());
        log.info(MessageConstant.ENTITY_DELETED.name().concat(" ".concat(admin.toString())));
        wasDeleted = true;

        return wasDeleted;
    }

    @Override
    public Boolean defaultDelete(String filter) {
        log.info(MessageConstant.INVALID_VALUE.name().concat(" ".concat(filter)));
        return false;
    }
}
