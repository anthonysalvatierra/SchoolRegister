package com.roche.SchoolRegister.SchoolRegister.dao.daoImpl;

import com.roche.SchoolRegister.SchoolRegister.dao.Idao.IAdminDao;
import com.roche.SchoolRegister.SchoolRegister.entities.Admin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminDaoImpl {

    @Autowired
    private IAdminDao adminDao;

    @PersistenceContext
    private EntityManager em;

    public List<Admin> findAll(){
        return (List<Admin>) this.adminDao.findAll();
    }

    public Admin save(Admin admin){
        return this.adminDao.save(admin);
    }

    public void delete(Admin admin){
        this.adminDao.delete(admin);
    }

    public Optional<Admin> findById(Long id){
        return this.adminDao.findById(id);
    }

    public Admin findByDna(String dna){
        return (Admin) this.em.createQuery(IAdminDao.finByDna)
                .setParameter("dna", dna)
                .getSingleResult();
    }

    public List findByName(String name){
        return this.em.createQuery(IAdminDao.findByName)
                .setParameter("name", name)
                .getResultList();
    }

}
