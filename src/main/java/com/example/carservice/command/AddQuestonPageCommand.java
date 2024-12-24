package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.ServiceError;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddQuestonPageCommand implements Command {
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long testId = Long.parseLong(request.getQueryString().split("\\&")[1].replace("testId=",""));
        request.addAttributeToJsp("testId", testId);
        return requestFactory.createForwardResponse(PagePath.QUESTION_ADD_PAGE.getPath());
    }
}
