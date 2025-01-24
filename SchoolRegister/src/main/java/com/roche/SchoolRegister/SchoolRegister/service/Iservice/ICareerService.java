package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Career;

import java.util.List;

public interface ICareerService {

    List<Career> findAll();

    Career save(Career career);

}

