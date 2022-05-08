package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    }
}
