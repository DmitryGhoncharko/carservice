package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Order;
import com.example.carservice.entity.Service;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.CarServiceService;

import java.util.List;

public class ShowServicesClientCommand implements Command{
    private final RequestFactory requestFactory;
    private final CarServiceService serviceService;

    public ShowServicesClientCommand(RequestFactory requestFactory, CarServiceService serviceService) {
        this.requestFactory = requestFactory;
        this.serviceService = serviceService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        final List<Service> serviceList = serviceService.getAllServices();
        request.addAttributeToJsp("services",serviceList);
        return  requestFactory.createForwardResponse(PagePath.CREATE_ORDER_PAGE.getPath());
    }
}
