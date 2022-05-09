package com.example.carservice.command;

public enum PagePath {
    MAIN_PAGE("/WEB-INF/jsp/main.jsp"), LOGIN_PAGE("/WEB-INF/jsp/login.jsp"), INDEX_PATH("/"),
    REGISTRATION_PAGE("/WEB-INF/jsp/registration.jsp"), LOCATION_PAGE("/WEB-INF/jsp/location.jsp"),
    SERVICE_PAGE("/WEB-INF/jsp/service.jsp"), ADD_SERVICE_PAGE("/WEB-INF/jsp/addservice.jsp");
    private final String path;

    PagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
