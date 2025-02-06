package com.roche.SchoolRegister.SchoolRegister.service.Iservice;
import jakarta.servlet.http.HttpServletRequest;

public interface IEditService {

    String editEntity(String id, String entity, HttpServletRequest request);

    String updateStudent(String id, HttpServletRequest request);

    String updateTeacher(String id, HttpServletRequest request);

    String updateAdmin(String id, HttpServletRequest request);

}
