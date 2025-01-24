package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.ITeacherDao;
import com.roche.SchoolRegister.SchoolRegister.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDaoImpl {

    @Autowired
    private ITeacherDao teacherDao;

    public List<Teacher> findAll(){
        return (List<Teacher>) this.teacherDao.findAll();
    }

    public Teacher save(Teacher teacher){
        return this.teacherDao.save(teacher);
    }

}
