package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.CarServiceService;

public class ShowAddPageCommand implements Command{
    private final RequestFactory requestFactory;

    public ShowAddPageCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createRedirectResponse(PagePath.ADD_SERVICE_PAGE.getPath());
    }
}
