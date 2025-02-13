package com.roche.SchoolRegister.SchoolRegister.controllers;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.constants.PathConstant;
import com.roche.SchoolRegister.SchoolRegister.entities.*;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.*;
import com.roche.SchoolRegister.SchoolRegister.service.serviceImpl.FilterServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @Autowired
    private IDeleteService deleteService;

    @Autowired
    private IEditService editService;

    @Autowired
    private FilterServiceImpl filterService;

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
            return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
        }
        log.info("{} {}", MessageConstant.ENTITY_INSERTED.name(), student);

        this.loadableService.constructUser(student);

        attributes.addFlashAttribute(MessageConstant.STUDENT_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddStudents");
        return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute @Valid Teacher teacher,
                              BindingResult result, RedirectAttributes attributes, Model model) {

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

        this.loadableService.constructUser(teacher);

        attributes.addFlashAttribute(MessageConstant.TEACHER_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddTeachers");
        return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute @Valid Course course,
                              BindingResult result, RedirectAttributes attributes, Model model) {

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

        attributes.addFlashAttribute(MessageConstant.COURSE_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddCourses");
        return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
    }

    @PostMapping("/saveAdmin")
    public String saveAdmin(@ModelAttribute @Valid Admin admin,
                              BindingResult result, RedirectAttributes attributes, Model model) {

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

        this.loadableService.constructUser(admin);

        attributes.addFlashAttribute(MessageConstant.ADMIN_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddAdmins");
        return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
    }

    @PostMapping("/saveCareer")
    public String saveCareer(@ModelAttribute @Valid Career career,
                            BindingResult result, RedirectAttributes attributes, Model model) {

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

        attributes.addFlashAttribute(MessageConstant.CAREER_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "toHome-toAddCareers");
        return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, @RequestParam("filter") String filter,
                         Model model, RedirectAttributes attributes){

        boolean wasDeleted = this.deleteService.deleteEntity(id, filter);

        model.addAttribute(MessageConstant.ENTITIES.name().toLowerCase(), this.loadableService.loadEntities());
        this.loadableService.loadData()
                .forEach(obj -> model.addAttribute(this.loadableService.nameClass(obj.getClass().getName()).toLowerCase(), obj));

        if(!wasDeleted){
            model.addAttribute(MessageConstant.ERROR_DELETED_ENTITY.name().concat("_".concat(filter)).toLowerCase(), MessageConstant.MESSAGE);
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "to".concat(filter.split("")[0].toUpperCase()
                    .concat(filter.substring(1).concat("-"))));
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }

        attributes.addFlashAttribute(MessageConstant.DELETED_SUCCESS.name().concat("_".concat(filter)).toLowerCase(), MessageConstant.MESSAGE);
        attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "to".concat(filter.split("")[0].toUpperCase()
                .concat(filter.substring(1).concat("-"))));
        return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
    }

    @RequestMapping("/editEntity")
    public String editEntity(@RequestParam("entity") String entity, HttpServletRequest request,
                             Model model, RedirectAttributes attributes){

        String id = request.getParameter("id");

        if(id.trim().isEmpty()){
            log.info(MessageConstant.ID_DOES_NOT_EXIST.name());
            model.addAttribute(MessageConstant.ERROR_UPDATING_ENTITY.name().concat("_".concat(entity)).toLowerCase(), MessageConstant.MESSAGE);
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "to".concat(entity.split("")[0].toUpperCase()
                    .concat(entity.substring(1).concat("-"))));
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }

        String message = this.editService.editEntity(id, entity, request);

        model.addAttribute(MessageConstant.ENTITIES.name().toLowerCase(), this.loadableService.loadEntities());
        this.loadableService.loadData()
                .forEach(obj -> model.addAttribute(this.loadableService.nameClass(obj.getClass().getName()).toLowerCase(), obj));

        if(!MessageConstant.ENTITY_UPDATED_SUCCESSFULLY.name().equalsIgnoreCase(message)){
            log.info(MessageConstant.ERROR_UPDATING_ENTITY.name(), message);
            model.addAttribute(MessageConstant.ERROR_UPDATING_ENTITY.name().concat("_".concat(entity)).toLowerCase(), MessageConstant.MESSAGE);
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "to".concat(entity.split("")[0].toUpperCase()
                    .concat(entity.substring(1).concat("-"))));
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }

        attributes.addFlashAttribute(MessageConstant.UPDATED_SUCCESS.name().concat("_".concat(entity)).toLowerCase(), MessageConstant.MESSAGE);
        attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "to".concat(entity.split("")[0].toUpperCase()
                .concat(entity.substring(1).concat("-"))));

        return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
    }

    @RequestMapping("/filterEntity")
    public String filterEntity(@RequestParam("entity") String entity, HttpServletRequest request,
                               Model model, RedirectAttributes attributes){

        List entities = this.filterService.filterEntity(entity, request);

        model.addAttribute(MessageConstant.ENTITIES.name().toLowerCase(), this.loadableService.loadEntities());
        this.loadableService.loadData()
                .forEach(obj -> model.addAttribute(this.loadableService.nameClass(obj.getClass().getName()).toLowerCase(), obj));

        if(entities.isEmpty()){
            log.info(MessageConstant.ERROR_FILTERING_ENTITY.name());
            model.addAttribute(MessageConstant.ERROR_FILTERING_ENTITY.name().concat("_".concat(entity)).toLowerCase(), MessageConstant.MESSAGE);
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "to".concat(entity.split("")[0].toUpperCase()
                    .concat(entity.substring(1).concat("-"))));
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }

        if(entities.size() == 1  && !entity.equalsIgnoreCase(MessageConstant.USERS.name())){
            attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "to".concat(entity.split("")[0].toUpperCase()
                    .concat(entity.substring(1).concat("-"))).concat(entity).concat(((List<Person>) entities).get(0).getId().toString()));
            return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
        }

        attributes.addFlashAttribute("entities_".concat(entity), entities);
        attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "to".concat(entity.split("")[0].toUpperCase()
                .concat(entity.substring(1).concat("-")))
                        .concat(entity).concat("_")
                .concat(MessageConstant.FOUNDED.name().toLowerCase()));
        return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());
    }

    @GetMapping("/deleteCourse/{idCourse}")
    public String deleteCourseFromTeacher(@PathVariable("idCourse") Long idCourse, Model model, RedirectAttributes attributes){

        Optional<Course> course = Optional.empty();

        try{

            course = this.courseService.findById(idCourse);

            if(course.isPresent()){
                course.get().setTeacher(null);
                this.courseService.save(course.get());
            }

        }catch (Exception exception){
            model.addAttribute(MessageConstant.ENTITIES.name().toLowerCase(), this.loadableService.loadEntities());
            this.loadableService.loadData()
                    .forEach(obj -> model.addAttribute(this.loadableService.nameClass(obj.getClass().getName()).toLowerCase(), obj));
            log.info(MessageConstant.COURSE.name().concat(" ").concat(course.get().toString()).concat(" ").concat(exception.getMessage()));
            model.addAttribute(MessageConstant.ERROR_FILTERING_ENTITY.name().toLowerCase(), MessageConstant.MESSAGE);
            model.addAttribute(MessageConstant.GOTO.name().toLowerCase(), "toTeachers-");
            return PathConstant.ADMIN_DASHBOARD.getPath();
        }

        attributes.addFlashAttribute(MessageConstant.UPDATED_SUCCESS.name().toLowerCase(), MessageConstant.MESSAGE);
        attributes.addFlashAttribute(MessageConstant.GOTO.name().toLowerCase(), "toTeachers-");
        return "redirect:".concat(PathConstant.INDEX_DASHBOARD_REDIRECT.getPath());

    }

}
