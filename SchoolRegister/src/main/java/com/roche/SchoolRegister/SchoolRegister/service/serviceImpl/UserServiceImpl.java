package com.roche.SchoolRegister.SchoolRegister.service.serviceImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.dao.Idao.IUserDao;
import com.roche.SchoolRegister.SchoolRegister.dao.daoImpl.UserDaoImpl;
import com.roche.SchoolRegister.SchoolRegister.entities.Person;
import com.roche.SchoolRegister.SchoolRegister.entities.User;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.ILoadableService;
import com.roche.SchoolRegister.SchoolRegister.service.Iservice.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final Logger log = LogManager.getLogger(this);

    @Autowired
    private ILoadableService loadableService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserDaoImpl userImpl;

    @Override
    public User save(User user) {
        User userInserted = new User();

        try{
            userInserted = this.userDao.save(user);
        }catch (Exception exception){
            log.error(MessageConstant.ERROR_PERSIST_ENTITY.name().concat(" ")
                    .concat(exception.getMessage()));
        }

        return userInserted;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) this.userDao.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userImpl.findByUsername(username);
    }

    @Override
    public String findRoleByUsername(String username) {
        return this.userImpl.findRoleByUsername(username);
    }

    @Override
    public void constructUser(Person person) {

        User user = new User(person.getName(), person.getDna(),
                this.encoder.encode(person.getDna()), this.loadableService.nameClass(person.getClass().getName()));

        user = this.save(user);
        if(user.getId() != null){
            log.info("{} {}", MessageConstant.ENTITY_INSERTED.name(), user);
        }

    }

    @Override
    public void delete(User user) {
        this.userImpl.delete(user);
    }
}
