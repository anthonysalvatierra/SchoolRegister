package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.IAdminDao;
import com.roche.SchoolRegister.SchoolRegister.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl {

    @Autowired
    private IAdminDao adminDao;

    public Admin save(Admin admin){
        return this.adminDao.save(admin);
    }

}
