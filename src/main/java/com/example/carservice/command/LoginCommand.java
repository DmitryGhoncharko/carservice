package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.User;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class LoginCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(LoginCommand.class);
    private final RequestFactory requestFactory;
    private final UserService userService;

    public LoginCommand(RequestFactory requestFactory, UserService userService) {
        this.requestFactory = requestFactory;
        this.userService = userService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        if (request.sessionExists() && request.retrieveFromSession("user").isPresent()) {
            return requestFactory.createForwardResponse(PagePath.MAIN_PAGE.getPath());
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<User> userFromDB = userService.authenticateUser(login,password);
        if(!userFromDB.isPresent()){
            request.addAttributeToJsp("errorLoginPassMessage", "Неверный логин или пароль");
            return requestFactory.createForwardResponse(PagePath.LOGIN_PAGE.getPath());
        }
        request.clearSession();
        request.createSession();
        request.addToSession("user", userFromDB.get());
        return requestFactory.createRedirectResponse("/controller?command=main");
    }
}
