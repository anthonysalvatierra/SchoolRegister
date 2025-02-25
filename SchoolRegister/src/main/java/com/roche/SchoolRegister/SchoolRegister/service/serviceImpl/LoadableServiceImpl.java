package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.entities.*;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoadableServiceImpl implements ILoadableService {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private IStudentService studentService;

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ICareerService careerService;

    @Autowired
    private ILevelService levelService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Override
    public Map loadEntities() {

        return Map.of(
                MessageConstant.TEACHER.name().toLowerCase(), this.teacherService.findAll(),
                MessageConstant.CAREER.name().toLowerCase(), this.careerService.findAll(),
                MessageConstant.LEVEL.name().toLowerCase(), this.levelService.findAll(),
                MessageConstant.STUDENT.name().toLowerCase(), this.studentService.findAll(),
                MessageConstant.ADMIN.name().toLowerCase(), this.adminService.findAll(),
                MessageConstant.USER.name().toLowerCase(), this.userService.findAll(),
                MessageConstant.COURSE.name().toLowerCase(), this.courseService.findAll(),
                MessageConstant.COURSE_WITH_TEACHER.name().toLowerCase(), this.courseService.findCourseWhereTeacherIsNotNull()
        );

    }

    @Override
    public List<Object> loadData(){
        return new ArrayList<>(
                List.of(
                        new Teacher(),
                        new Course(),
                        new Admin(),
                        new Career(),
                        new Student()
                )
        );
    }

    @Override
    public String nameClass(String name){
        String[] names = name.split("\\.");
        return names[names.length - 1];
    }

    @Override
    public void loadAttributes(Model model, Object object) {
        model.addAttribute(MessageConstant.ENTITIES.name().toLowerCase(), this.loadEntities());

        for (Object obj : this.loadData()) {
            if (!(this.nameClass(obj.getClass().getName())
                    .equalsIgnoreCase(this.nameClass(object.getClass().getName())))) {

                model.addAttribute(this.nameClass(obj.getClass().getName()).toLowerCase(), obj);

            }
        }
    }

    @Override
    public void constructUser(Person person) {

            User user = new User(person.getName(), person.getDna(),
                    this.encoder.encode(person.getDna()), this.nameClass(person.getClass().getName()));

            user = this.userService.save(user);
            if(user.getId() != null){
                log.info("{} {}", MessageConstant.ENTITY_INSERTED.name(), user);
            }
    }
}
