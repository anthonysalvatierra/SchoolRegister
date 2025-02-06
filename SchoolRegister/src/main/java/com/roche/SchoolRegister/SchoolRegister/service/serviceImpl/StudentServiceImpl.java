package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.dao.daoImpl.StudentDaoImpl;
import com.roche.SchoolRegister.SchoolRegister.entities.Student;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IStudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Student> findAll() {
        return this.studentDao.findAll();
    }

    @Override
    public void delete(Student student) {
        this.studentDao.delete(student);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return this.studentDao.findById(id);
    }

    @Override
    public Student findByDna(String dna) {
        return this.studentDao.findByDna(dna);
    }

    @Override
    public List<Student> findByQuery(HttpServletRequest request) {
        return this.studentDao.findByQuery(request);
    }
}
