package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.ICareerDao;
import com.roche.SchoolRegister.SchoolRegister.entities.Career;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CareerDaoImpl {

    @Autowired
    private ICareerDao careerDao;

    public List<Career> findAll(){
        return (List<Career>) this.careerDao.findAll();
    }

    public Career save(Career career){
        return this.careerDao.save(career);
    }

    public void delete(Career career){
        this.careerDao.delete(career);
    }

    public Optional<Career> findById(Long id){
        return this.careerDao.findById(id);
    }

}
