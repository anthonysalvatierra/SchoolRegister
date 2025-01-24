package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.IStudentDao;
import com.roche.SchoolRegister.SchoolRegister.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl {

    @Autowired
    private IStudentDao studentDao;

    public Student save(Student student){
        return this.studentDao.save(student);
    }


}
