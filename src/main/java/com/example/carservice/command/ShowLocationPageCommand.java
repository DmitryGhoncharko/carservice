package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.ServiceError;

public class ShowLocationPageCommand implements Command{
    private final RequestFactory requestFactory;

    public ShowLocationPageCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.LOCATION_PAGE.getPath());
    }
}
