package com.example.carservice.command;


import com.example.carservice.controller.RequestFactory;
import com.example.carservice.controller.SimpleRequestFactory;
import com.example.carservice.model.connection.ConnectionPool;
import com.example.carservice.model.connection.HikariCPConnectionPool;
import com.example.carservice.model.dao.*;
import com.example.carservice.model.service.*;
import com.example.carservice.securiy.BcryptWithSaltHasherImpl;
import com.example.carservice.securiy.PasswordHasher;
import com.example.carservice.validator.SimpleUserValidator;
import com.example.carservice.validator.UserValidator;

public class InitialContext {
    private final ConnectionPool hikariCPConnectionPool = new HikariCPConnectionPool();
    private final PasswordHasher bcryptWithSaltHasher = new BcryptWithSaltHasherImpl();
    private final UserValidator userValidator = new SimpleUserValidator();
    private final UserDao simpleUserDao = new SimpleUserDao(hikariCPConnectionPool);
    private final UserService simpleUserService = new SimpleUserService(simpleUserDao,userValidator,bcryptWithSaltHasher);
    private final RequestFactory simpleRequestFactory = new SimpleRequestFactory();
    private final CarServiceDao simpleCarServiceDao = new SimpleCarServiceDao(hikariCPConnectionPool);
    private final CarServiceService simpleCarServiceService = new SimpleCarServiceService(simpleCarServiceDao);
    private final OrderDao orderDao = new SimpleOrderDao(hikariCPConnectionPool);
    private final OrderService orderService = new SimpleOrderService(orderDao);
    public Command lookup(String commandName) {

        switch (commandName) {
            case "login":
                return new ShowLoginPageCommand(simpleRequestFactory);
            case "logincmnd":
                return new LoginCommand(simpleRequestFactory, simpleUserService);
            case "registration":
                return new ShowRegistrationPage(simpleRequestFactory);
            case "registrationcmnd":
                return new RegistrationCommand(simpleRequestFactory,simpleUserService);
            case "logout":
                return new LogoutCommand(simpleRequestFactory);
            case "showservices":
                return new ShowServicesPageCommand(simpleRequestFactory,simpleCarServiceService);
            case "addService":
                return new AddServiceCommand(simpleRequestFactory,simpleCarServiceService);
            case "updateService":
                return new UpdateServiceCommand(simpleRequestFactory,simpleCarServiceService);
            case "deleteService":
                return new DeleteServiceCommand(simpleRequestFactory,simpleCarServiceService);
            case "location":
                return new ShowLocationPageCommand(simpleRequestFactory);
            case "addservicepage":
                return new ShowAddPageCommand(simpleRequestFactory);
            case "showadminorders":
                return new ShowAdminOrdersPage(simpleRequestFactory,orderService);
            case "endorder":
                return new SubmitOrderCommand(simpleRequestFactory,orderService);
            case "clientorders":
                return new ShowClientOrderPageCommand(simpleRequestFactory,orderService);
            case "addorderToClient":
                return new AddOrderClientCommand(simpleRequestFactory, orderService);
            case "createorderclient":
                return new ShowServicesClientCommand(simpleRequestFactory,simpleCarServiceService);
                default:
                return new ShowMainPageCommand(simpleRequestFactory);
        }

    }
}
