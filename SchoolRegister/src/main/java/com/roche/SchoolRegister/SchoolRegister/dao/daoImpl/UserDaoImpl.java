package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.IUserDao;
import com.roche.SchoolRegister.SchoolRegister.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements IUserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<?> findAll() {

        return this.em.createQuery(findAll).getResultList();

    }

    @Override
    public Optional<User> findByUsername(String username) {

        User user = (User) this.em.createQuery(findByUsername)
                .setParameter("username", username)
                .getSingleResult();

        return Optional.ofNullable(user);
    }

    @Override
    public String findRoleByUsername(String username) {
        return (String) this.em.createQuery(findRoleByUsername)
                .setParameter("username", username)
                .getSingleResult();
    }
}
