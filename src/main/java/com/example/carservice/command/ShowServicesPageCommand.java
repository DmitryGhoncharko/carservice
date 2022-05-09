package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Service;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.CarServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ShowServicesPageCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(ShowServicesPageCommand.class);
    private final RequestFactory requestFactory;
    private final CarServiceService carServiceService;

    public ShowServicesPageCommand(RequestFactory requestFactory, CarServiceService carServiceService) {
        this.requestFactory = requestFactory;
        this.carServiceService = carServiceService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        final List<Service> serviceList = carServiceService.getAllServices();
        request.addAttributeToJsp("services",serviceList);
        return requestFactory.createForwardResponse(PagePath.SERVICE_PAGE.getPath());
    }
}
