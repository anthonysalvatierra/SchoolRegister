package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.entities.*;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ICourseService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IFilterEntity;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IStudentService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ITeacherService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilterServiceImpl implements IFilterEntity {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ICourseService courseService;

    @Override
    public List filterEntity(String entity, HttpServletRequest request) {
        return switch (entity){
            case "students" -> this.filterStudent(request, entity);
            case "teachers" -> this.filterTeacher(request);
            case "admins" -> this.filterAdmin(request);
            case "careers" -> this.filterCareer(request);
            default -> this.defaultValue(entity);
        };
    }

    @Override
    public final List<Student> filterStudent(HttpServletRequest request, String entity) {

        Student student = null;
        String query;

        if(!request.getParameter("dna").isBlank()){
            try{
                student = this.studentService.findByDna(request.getParameter("dna").trim());
            }catch (Exception exception){
                log.info(MessageConstant.DNA_MAY_NOT_EXIST.name().concat(" ".concat(exception.getMessage())));
                return new ArrayList<>();
            }
        }else{

            List<Student> results = new ArrayList<>();

            try{
                results = this.studentService.findByQuery(request);
            }catch (Exception exception){
                log.info(MessageConstant.ERROR_FILTERING_ENTITY.name().concat(" ").concat(exception.getMessage()));
                return results;
            }

            return results;
        }

        if(student == null){
            return new ArrayList<>();
        }

        return List.of(student);

    }

    @Override
    public final List<Teacher> filterTeacher(HttpServletRequest request) {
        Teacher teacher = null;
        String query;

        if(!request.getParameter("dna").isBlank()){
            try{
                teacher = this.teacherService.findByDna(request.getParameter("dna").trim());
            }catch (Exception exception){
                log.info(MessageConstant.DNA_MAY_NOT_EXIST.name().concat(" ".concat(exception.getMessage())));
                return new ArrayList<>();
            }
        }else if(!request.getParameter("name").isBlank()){
            List<Teacher> results = new ArrayList<>();

            try{
                results = this.teacherService.findByName(request.getParameter("name").trim());
            } catch (Exception exception) {
                log.info(MessageConstant.ERROR_FILTERING_ENTITY.name().concat(" ").concat(exception.getMessage()));
                return results;
            }

            return results;

        } else if (!request.getParameter("course").isBlank()) {

            Optional<Course> course = this.courseService.findById(Long.parseLong(request.getParameter("course")));

            if(course.isPresent() && course.get().getTeacher() != null){
                teacher = course.get().getTeacher();
            }

        }

        if(teacher == null){
            return new ArrayList<>();
        }

        return List.of(teacher);
    }

    @Override
    public final List<Admin> filterAdmin(HttpServletRequest request) {
        return null;
    }

    @Override
    public final List<Career> filterCareer(HttpServletRequest request) {
        return null;
    }

    @Override
    public final List<Person> defaultValue(String entity) {
        log.info(MessageConstant.INVALID_VALUE.name().concat(" ".concat(entity)));
        return new ArrayList<>();
    }
}
