package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.entities.*;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EditServiceImpl implements IEditService {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private ICareerService careerService;

    @Autowired
    private ILevelService levelService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ICourseService courseService;

    @Override
    public String editEntity(String id, String entity, HttpServletRequest request) {

        return switch (entity){
            case "students" -> this.updateStudent(id, request);
            case "teachers" -> this.updateTeacher(id, request);
            case "admins" -> this.updateAdmin(id, request);
            default -> this.defaultService();
        };

    }

    @Override
    public String updateStudent(String id, HttpServletRequest request) {

        Student student = new Student();
        student.setId(Long.parseLong(id));
        student.setName(request.getParameter("name"));
        student.setDna(request.getParameter("dna"));
        student.setPhoneNumber(request.getParameter("phoneNumber"));
        student.setEmail(request.getParameter("email"));
        student.setStatus(request.getParameter("status"));

        Optional<Career> career = this.careerService.findById(Long.parseLong(request.getParameter("career")));
        career.ifPresent(student::setCareer);

        Optional<Level> level = this.levelService.findById(Long.parseLong(request.getParameter("level")));
        level.ifPresent(student::setLevel);

        try{
            student = this.studentService.save(student);
        }catch (Exception e){
            log.info(MessageConstant.ERROR_UPDATING_ENTITY.name().concat(" ").concat(e.getMessage()));
            return MessageConstant.ERROR_UPDATING_ENTITY.name().toLowerCase();
        }

        log.info(MessageConstant.ENTITY_UPDATED_SUCCESSFULLY.name().concat(" ").concat(student.toString()));
        return MessageConstant.ENTITY_UPDATED_SUCCESSFULLY.name().toLowerCase();
    }

    @Override
    public String updateTeacher(String id, HttpServletRequest request) {

        Optional<Teacher> teacher = this.teacherService.findById(Long.parseLong(id));

        if (teacher.isEmpty()){
            log.info(MessageConstant.ERROR_UPDATING_ENTITY.name().concat(" ").concat(id));
        }

        teacher.get().setName(request.getParameter("name"));
        teacher.get().setDna(request.getParameter("dna"));
        teacher.get().setAddress(request.getParameter("address"));
        teacher.get().setPhoneNumber(request.getParameter("phoneNumber"));
        teacher.get().setEmail(request.getParameter("email"));

        Teacher teacherFound = this.teacherService.save(teacher.get());

        if(!request.getParameter("course").trim().isEmpty()){

            Optional<Course> course = this.courseService.findById(Long.parseLong(request.getParameter("course")));

            if(course.isPresent() && course.get().getTeacher() == null){

                if(this.checkAmountCourses(teacherFound)){
                    course.get().setTeacher(teacherFound);
                    this.courseService.save(course.get());
                }

            }

            log.info(MessageConstant.COURSE.name().concat(" ").concat(course.get().toString()));
        }

        log.info(MessageConstant.ENTITY_UPDATED_SUCCESSFULLY.name().concat(" ").concat(teacherFound.toString()));
        return MessageConstant.ENTITY_UPDATED_SUCCESSFULLY.name().toLowerCase();
    }

    @Override
    public String updateAdmin(String id, HttpServletRequest request) {
        return "";
    }

    public String defaultService(){
        log.info(MessageConstant.PARAMETER_ENTITY_NOT_VALID.name().toLowerCase());
        return MessageConstant.PARAMETER_ENTITY_NOT_VALID.name().toLowerCase();
    }

    public boolean checkAmountCourses(Teacher teacher){
        boolean flag = false;
        AtomicInteger amount = new AtomicInteger();
        List<Course> courses = this.courseService.findCourseWhereTeacherIsNotNull();

        courses.forEach(c -> {
            if(c.getTeacher().equals(teacher)){
                amount.addAndGet(1);
            }
        });

        if(amount.get() >= 3){
            log.info(MessageConstant.OVER_AMOUNT.name().concat(" ").concat(String.valueOf(amount.get())));
            return flag;
        }

        flag = true;
        return flag;
    }

}
