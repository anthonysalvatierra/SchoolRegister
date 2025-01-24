package com.roche.SchoolRegister.SchoolRegister.service.Iservice;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

public interface ILoadableService {

    Map<String, List<Object>> loadEntities();

    List<Object> loadData();

    String nameClass(String name);

    void loadAttributes(Model model, Object object);

}
