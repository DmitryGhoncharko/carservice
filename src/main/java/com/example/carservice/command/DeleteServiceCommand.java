package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.CarServiceService;

public class DeleteServiceCommand implements Command{
    private final RequestFactory requestFactory;
    private final CarServiceService serviceService;

    public DeleteServiceCommand(RequestFactory requestFactory, CarServiceService serviceService) {
        this.requestFactory = requestFactory;
        this.serviceService = serviceService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        final long serviceId = Long.valueOf("serviceId");
        serviceService.deleteService(serviceId);
        return requestFactory.createRedirectResponse("/controller?command=deleteService");
    }
}
