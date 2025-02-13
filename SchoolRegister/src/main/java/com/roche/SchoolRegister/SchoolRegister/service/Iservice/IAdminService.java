package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Admin;

import java.util.List;
import java.util.Optional;

public interface IAdminService {

    List<Admin> findAll();

    Admin save(Admin admin);

    void delete(Admin admin);

    Optional<Admin> findById(Long id);

    Admin findByDna(String dna);

    List<Admin> findByName(String name);

}
