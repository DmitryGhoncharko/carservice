package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddTestPageCommand implements Command {
    private final RequestFactory requestFactory;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        return requestFactory.createForwardResponse(PagePath.ADD_TEST_PAGE.getPath());
    }
}
