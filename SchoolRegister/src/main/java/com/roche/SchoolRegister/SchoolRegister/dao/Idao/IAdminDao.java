package com.roche.SchoolRegister.SchoolRegister.dao.Idao;

import com.roche.SchoolRegister.SchoolRegister.entities.Admin;
import org.springframework.data.repository.CrudRepository;

public interface IAdminDao extends CrudRepository<Admin, Long> {

    String finByDna = "SELECT a FROM Admin a WHERE dna = :dna";

    String findByName = "SELECT a FROM Admin a WHERE LOWER(name) = LOWER(:name)";

}
