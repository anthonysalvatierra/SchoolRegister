package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.ILevelDao;
import com.roche.SchoolRegister.SchoolRegister.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LevelDaoImpl {

    @Autowired
    private ILevelDao levelDao;

    public List<Level> findAll(){
        return (List<Level>) this.levelDao.findAll();
    }

}
