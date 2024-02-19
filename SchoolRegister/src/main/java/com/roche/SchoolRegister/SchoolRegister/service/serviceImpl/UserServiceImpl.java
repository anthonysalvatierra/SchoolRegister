package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.IUserDao;
import com.roche.SchoolRegister.SchoolRegister.entities.User;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public List<?> findAll() {
        return this.userDao.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userDao.findByUsername(username);
    }
}
