package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.dao.daoImpl.AdminDaoImpl;
import com.roche.SchoolRegister.SchoolRegister.entities.Admin;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IAdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements IAdminService {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private AdminDaoImpl adminDao;

    @Override
    public List<Admin> findAll() {
        return this.adminDao.findAll();
    }

    @Override
    public Admin save(Admin admin) {
        Admin adminInserted = new Admin();

        try{
            adminInserted = this.adminDao.save(admin);
        } catch (Exception exception) {
            log.error(MessageConstant.ERROR_PERSIST_ENTITY.name().concat(" ")
                    .concat(exception.getMessage()));
        }
        return adminInserted;
    }

    @Override
    public void delete(Admin admin) {
        this.adminDao.delete(admin);
    }

    @Override
    public Optional<Admin> findById(Long id) {
        return this.adminDao.findById(id);
    }

    @Override
    public Admin findByDna(String dna) {
        return this.adminDao.findByDna(dna);
    }

    @Override
    public List<Admin> findByName(String name) {
        return this.adminDao.findByName(name);
    }
}
