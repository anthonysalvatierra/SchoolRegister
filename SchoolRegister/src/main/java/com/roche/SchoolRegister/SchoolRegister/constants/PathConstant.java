package com.roche.SchoolRegister.SchoolRegister.constants;

public enum PathConstant {

    ADMIN_DASHBOARD("admin/dashboard"),
    INDEX_DASHBOARD_REDIRECT("/index/dashboard");

    private final String path;

    PathConstant(String path){
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }

}
