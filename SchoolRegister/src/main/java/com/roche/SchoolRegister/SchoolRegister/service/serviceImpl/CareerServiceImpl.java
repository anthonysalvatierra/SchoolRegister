package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.dao.daoImpl.CareerDaoImpl;
import com.roche.SchoolRegister.SchoolRegister.entities.Career;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ICareerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CareerServiceImpl implements ICareerService {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private CareerDaoImpl careerDao;

    @Override
    public List<Career> findAll() {
        return this.careerDao.findAll();
    }

    @Override
    public Career save(Career career) {
        Career careerInserted = new Career();

        try{
            careerInserted = this.careerDao.save(career);
        }catch (Exception exception){
            log.error(MessageConstant.ERROR_PERSIST_ENTITY.name().concat(" ")
                    .concat(exception.getMessage()));
        }
        return careerInserted;
    }
}
