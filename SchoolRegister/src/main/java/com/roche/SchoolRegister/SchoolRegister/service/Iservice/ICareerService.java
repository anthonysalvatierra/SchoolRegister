package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Career;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ICareerService {

    List<Career> findAll();

    Career save(Career career);

    void delete(Career career);

    public Optional<Career> findById(Long id);

}

