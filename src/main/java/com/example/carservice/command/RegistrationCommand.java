package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(RegistrationCommand.class);
    private final RequestFactory requestFactory;
    private final UserService userService;

    public RegistrationCommand(RequestFactory requestFactory, UserService userService) {
        this.requestFactory = requestFactory;
        this.userService = userService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        userService.addUserAsClient(login,password);
        return requestFactory.createRedirectResponse("/controller?command=main");
    }
}
