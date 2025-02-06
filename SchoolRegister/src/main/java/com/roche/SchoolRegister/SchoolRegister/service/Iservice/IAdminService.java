package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import com.roche.SchoolRegister.SchoolRegister.entities.Admin;

import java.util.Optional;

public interface IAdminService {

    Admin save(Admin admin);

    void delete(Admin admin);

    public Optional<Admin> findById(Long id);

}
