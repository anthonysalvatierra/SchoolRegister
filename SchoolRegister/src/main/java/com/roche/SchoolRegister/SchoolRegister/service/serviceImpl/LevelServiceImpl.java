package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.daoImpl.LevelDaoImpl;
import com.roche.SchoolRegister.SchoolRegister.entities.Level;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ILevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements ILevelService {

    @Autowired
    private LevelDaoImpl levelDao;

    @Override
    public List<Level> findAll() {
        return this.levelDao.findAll();
    }
}
