package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.entities.*;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ICareerService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ILevelService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ILoadableService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoadableServiceImpl implements ILoadableService {

    @Autowired
    private ITeacherService teacherService;

    @Autowired
    private ICareerService careerService;

    @Autowired
    private ILevelService levelService;


    @Override
    public Map loadEntities() {

        return Map.of(
                MessageConstant.TEACHER.name().toLowerCase(), this.teacherService.findAll(),
                MessageConstant.CAREER.name().toLowerCase(), this.careerService.findAll(),
                MessageConstant.LEVEL.name().toLowerCase(), this.levelService.findAll()
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
}
