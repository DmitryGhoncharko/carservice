package com.example.carservice.command;


import com.example.carservice.controller.RequestFactory;
import com.example.carservice.controller.SimpleRequestFactory;
import com.example.carservice.model.connection.ConnectionPool;
import com.example.carservice.model.connection.HikariCPConnectionPool;
import com.example.carservice.securiy.BcryptWithSaltHasherImpl;
import com.example.carservice.securiy.PasswordHasher;

public class InitialContext {
    private final ConnectionPool hikariCPConnectionPool = new HikariCPConnectionPool();
    private final PasswordHasher bcryptWithSaltHasher = new BcryptWithSaltHasherImpl();

    private final RequestFactory simpleRequestFactory = new SimpleRequestFactory();

    public Command lookup(String commandName) {

        switch (commandName) {
            case "login":
                return new ShowLoginPageCommand(simpleRequestFactory);
            default:
                return new ShowMainPageCommand(simpleRequestFactory);

        }

    }
}
