package com.roche.SchoolRegister.SchoolRegister.dao.Idao;

import com.roche.SchoolRegister.SchoolRegister.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface ITeacherDao extends CrudRepository<Teacher, Long> {

}
