package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.dao.daoImpl.StudentDaoImpl;
import com.roche.SchoolRegister.SchoolRegister.entities.Student;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IStudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private StudentDaoImpl studentDao;

    @Override
    public Student save(Student student) {
        Student studentInserted = new Student();

        try{
            studentInserted = this.studentDao.save(student);
        }catch (Exception exception){
            log.error(MessageConstant.ERROR_PERSIST_ENTITY.name().concat(" ")
                    .concat(exception.getMessage()));
        }

        return studentInserted;
    }
}
