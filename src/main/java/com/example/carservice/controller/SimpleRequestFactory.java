package com.example.carservice.controller;

import com.example.carservice.command.CommandRequest;
import com.example.carservice.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

public class SimpleRequestFactory implements RequestFactory {

    @Override
    public CommandRequest createRequest(HttpServletRequest request) {
        return new WrappingCommandRequest(request);
    }

    @Override
    public CommandResponse createForwardResponse(String path) {
        return new PlainCommandResponse(path);
    }

    @Override
    public CommandResponse createRedirectResponse(String path) {
        return new PlainCommandResponse(true,path);
    }
}
