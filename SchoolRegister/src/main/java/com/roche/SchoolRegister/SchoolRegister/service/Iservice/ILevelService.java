package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Level;

import java.util.List;
import java.util.Optional;

public interface ILevelService {

    List<Level> findAll();

    Optional<Level> findById(Long id);

}
