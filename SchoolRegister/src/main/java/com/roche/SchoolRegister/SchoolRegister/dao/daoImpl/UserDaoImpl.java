package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.IUserDao;
import com.roche.SchoolRegister.SchoolRegister.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private IUserDao userDao;

    public List<User> findAll(){
        return (List<User>) this.userDao.findAll();
    }

    public Optional<User> findByUsername(String username) {

        User user = (User) this.em.createQuery(IUserDao.findByUsername)
                .setParameter("username", username)
                .getSingleResult();

        return Optional.ofNullable(user);
    }

    public String findRoleByUsername(String username) {
        return (String) this.em.createQuery(IUserDao.findRoleByUsername)
                .setParameter("username", username)
                .getSingleResult();
    }

    public void delete(User user){
        this.userDao.delete(user);
    }
}
