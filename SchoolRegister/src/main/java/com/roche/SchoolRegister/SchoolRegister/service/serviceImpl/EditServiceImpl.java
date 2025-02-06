package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.entities.Career;
import com.roche.SchoolRegister.SchoolRegister.entities.Level;
import com.roche.SchoolRegister.SchoolRegister.entities.Student;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ICareerService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IEditService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ILevelService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IStudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EditServiceImpl implements IEditService {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private ICareerService careerService;

    @Autowired
    private ILevelService levelService;

    @Autowired
    private IStudentService studentService;

    @Override
    public String editEntity(String id, String entity, HttpServletRequest request) {

        return switch (entity){
            case "student" -> this.updateStudent(id, request);
            case "teacher" -> this.updateTeacher(id, request);
            case "admin" -> this.updateAdmin(id, request);
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
        return "";
    }

    @Override
    public String updateAdmin(String id, HttpServletRequest request) {
        return "";
    }

    public String defaultService(){
        log.info(MessageConstant.PARAMETER_ENTITY_NOT_VALID.name().toLowerCase());
        return MessageConstant.PARAMETER_ENTITY_NOT_VALID.name().toLowerCase();
    }

}
