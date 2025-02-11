package com.roche.SchoolRegister.SchoolRegister.dao.Idao;

import com.roche.SchoolRegister.SchoolRegister.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface ITeacherDao extends CrudRepository<Teacher, Long> {

    String finByDna = "SELECT t FROM Teacher t WHERE dna = :dna";

    String findByName = "SELECT t FROM Teacher t WHERE LOWER(name) = LOWER(:name)";

}
