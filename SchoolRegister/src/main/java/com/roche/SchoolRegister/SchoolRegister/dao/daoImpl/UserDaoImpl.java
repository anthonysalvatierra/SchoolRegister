package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.constants.MessageConstant;
import com.roche.SchoolRegister.SchoolRegister.dao.Idao.IUserDao;
import com.roche.SchoolRegister.SchoolRegister.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDaoImpl {

    private final Logger log = LogManager.getLogger(this);

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

    public List<User> findByQuery(HttpServletRequest request){

        String name = request.getParameter("name").trim();
        String role = request.getParameter("role").trim();

        StringBuilder query = new StringBuilder("SELECT u FROM User u WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if(!name.isEmpty()){
            query.append(" AND LOWER(u.name) = LOWER(:name)");
            params.put("name", name);
        }
        if(!role.isEmpty()){
            query.append(" AND UPPER(u.role) = UPPER(:role)");
            params.put("role", role);
        }

        if(params.isEmpty()){
            log.info(MessageConstant.EMPTY_PARAMS.name().concat(" ").concat(query.toString()));
            return new ArrayList<>();
        }

        TypedQuery<User> consult = this.em.createQuery(query.toString(), User.class);
        params.forEach(consult::setParameter);

        List<User> users = consult.getResultList();

        log.info(users.toString());
        return  users;
    }
}
