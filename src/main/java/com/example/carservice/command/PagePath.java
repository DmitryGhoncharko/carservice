package com.example.carservice.command;

public enum PagePath {
    MAIN_PAGE("/WEB-INF/jsp/main.jsp"), LOGIN_PAGE("/WEB-INF/jsp/login.jsp"), ORDERS_PAGE("/WEB-INF/jsp/orders.jsp"),
    REGISTRATION_PAGE("/WEB-INF/jsp/registration.jsp"), LOCATION_PAGE("/WEB-INF/jsp/location.jsp"), CLIENT_ORDERS_PAGE("/WEB-INF/jsp/clientorder.jsp"),
    SERVICE_PAGE("/WEB-INF/jsp/service.jsp"), ADD_SERVICE_PAGE("/WEB-INF/jsp/addservice.jsp"), CREATE_ORDER_PAGE("/WEB-INF/jsp/createorder.jsp");
    private final String path;

    PagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
