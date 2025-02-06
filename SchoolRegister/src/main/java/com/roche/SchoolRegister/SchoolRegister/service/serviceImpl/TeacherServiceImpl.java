package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.dao.daoImpl.TeacherDaoImpl;
import com.roche.SchoolRegister.SchoolRegister.entities.Teacher;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ITeacherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements ITeacherService {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private TeacherDaoImpl teacherDao;

    @Override
    public List<Teacher> findAll() {
        return this.teacherDao.findAll();
    }

    @Override
    public Teacher save(Teacher teacher) {
        Teacher teacherInserted = new Teacher();

        try{
            teacherInserted = this.teacherDao.save(teacher);
        }catch (Exception exception){
            log.error(MessageConstant.ERROR_PERSIST_ENTITY.name().concat(" ")
                    .concat(exception.getMessage()));
        }
        return teacherInserted;
    }

    @Override
    public void delete(Teacher teacher) {
        this.teacherDao.delete(teacher);
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return this.teacherDao.findById(id);
    }
}
