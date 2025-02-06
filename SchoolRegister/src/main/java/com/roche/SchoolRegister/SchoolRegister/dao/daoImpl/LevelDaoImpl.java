package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.ILevelDao;
import com.roche.SchoolRegister.SchoolRegister.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LevelDaoImpl {

    @Autowired
    private ILevelDao levelDao;

    public List<Level> findAll(){
        return (List<Level>) this.levelDao.findAll();
    }

    public Optional<Level> findById(Long id){
        return this.levelDao.findById(id);
    }

}
