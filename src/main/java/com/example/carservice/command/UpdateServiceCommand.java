package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.CarServiceService;

public class UpdateServiceCommand implements Command{
    private final RequestFactory requestFactory;
    private final CarServiceService carServiceService;

    public UpdateServiceCommand(RequestFactory requestFactory, CarServiceService carServiceService) {
        this.requestFactory = requestFactory;
        this.carServiceService = carServiceService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        final long serviceId = Long.parseLong(request.getParameter("serviceId"));
        final String serviceName = request.getParameter("newServiceName");
        final int serviceCost = Integer.parseInt(request.getParameter("serviceCost"));
        carServiceService.updateService(serviceId,serviceName,serviceCost);
        return requestFactory.createRedirectResponse("/controller?command=main");
    }
}
