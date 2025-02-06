package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

public interface IDeleteService {

    Boolean deleteEntity(Long id, String filter);

    Boolean deleteStudent(Long id);

    Boolean deleteTeacher(Long id);

    Boolean deleteCareer(Long id);

    Boolean deleteAdmin(Long id);

    Boolean defaultDelete(String filter);

}
