package com.roche.SchoolRegister.SchoolRegister.controllers;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.constants.PathConstant;
import com.roche.SchoolRegister.SchoolRegister.entities.*;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.*;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private ICareerService careerService;

    @Autowired
    private ILoadableService loadableService;

    @Autowired
    private IUserService userService;

    private final Logger log = LogManager.getLogger(this);

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute @Valid Student student,
                              BindingResult result, RedirectAttributes attributes, Model model) {

        this.loadableService.loadAttributes(model, student);

        if (result.hasErrors()) {
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddStudents");
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }

        student = this.studentService.save(student);
        if(student.getId() == null){
            attributes.addFlashAttribute(MessageConstant.ERROR_INSERTION_STUDENT.name().toLowerCase(), MessageConstant.MESSAGE);
            attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddStudents");
            return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
        }
        log.info("{} {}", MessageConstant.ENTITY_INSERTED.name(), student);

        this.userService.constructUser(student);

        attributes.addFlashAttribute(MessageConstant.STUDENT_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddStudents");
        return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute @Valid Teacher teacher,
                              BindingResult result, Model model) {

        this.loadableService.loadAttributes(model, teacher);

        if (result.hasErrors()) {
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddTeachers");
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }

        teacher = this.teacherService.save(teacher);
        if(teacher.getId() == null){
            model.addAttribute(MessageConstant.ERROR_INSERTION_TEACHER.name().toLowerCase(), MessageConstant.MESSAGE);
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddTeachers");
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }
        log.info("{} {}", MessageConstant.ENTITY_INSERTED.name(), teacher);

        this.userService.constructUser(teacher);

        model.addAttribute(MessageConstant.TEACHER_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddTeachers");
        return PathConstant.ADMIN_DASHBOARD.getPath();
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute @Valid Course course,
                              BindingResult result, Model model) {

        this.loadableService.loadAttributes(model, course);

        if (result.hasErrors()) {
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddCourses");
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }

        course = this.courseService.save(course);
        if(course.getId() == null){
            model.addAttribute(MessageConstant.ERROR_INSERTION_COURSE.name().toLowerCase(), MessageConstant.MESSAGE);
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddCourses");
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }
        log.info("{} {}", MessageConstant.ENTITY_INSERTED.name(), course);

        model.addAttribute(MessageConstant.COURSE_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddCourses");
        return PathConstant.ADMIN_DASHBOARD.getPath();
    }

    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute @Valid Admin admin,
                              BindingResult result, Model model) {

        this.loadableService.loadAttributes(model, admin);

        if (result.hasErrors()) {
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddAdmins");
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }

        admin = this.adminService.save(admin);
        if(admin.getId() == null){
            model.addAttribute(MessageConstant.ERROR_INSERTION_ADMIN.name().toLowerCase(), MessageConstant.MESSAGE);
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddAdmins");
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }
        log.info("{} {}", MessageConstant.ENTITY_INSERTED.name(), admin);

        this.userService.constructUser(admin);

        model.addAttribute(MessageConstant.ADMIN_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddAdmins");
        return PathConstant.ADMIN_DASHBOARD.getPath();
    }

    @PostMapping("/saveCareer")
    public String saveCareer(@ModelAttribute @Valid Career career,
                            BindingResult result, Model model) {

        this.loadableService.loadAttributes(model, career);

        if (result.hasErrors()) {
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddCareers");
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }

        career = this.careerService.save(career);
        if(career.getId() == null){
            model.addAttribute(MessageConstant.ERROR_INSERTION_CAREER.name().toLowerCase(), MessageConstant.MESSAGE);
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddCareers");
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }
        log.info("{} {}", MessageConstant.ENTITY_INSERTED.name(), career);

        model.addAttribute(MessageConstant.CAREER_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddCareers");
        return PathConstant.ADMIN_DASHBOARD.getPath();
    }

}
