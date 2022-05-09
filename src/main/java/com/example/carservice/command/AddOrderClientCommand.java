package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Order;
import com.example.carservice.entity.Service;
import com.example.carservice.entity.User;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.OrderService;

import java.util.List;
import java.util.Optional;

public class AddOrderClientCommand implements Command {
    private final RequestFactory requestFactory;
    private final OrderService orderService;

    public AddOrderClientCommand(RequestFactory requestFactory, OrderService orderService) {
        this.requestFactory = requestFactory;
        this.orderService = orderService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long serviceId = Long.valueOf(request.getParameter("serviceId"));
        Optional<Object> userAsObjcet = request.retrieveFromSession("user");
        if (userAsObjcet.isPresent()) {
            User user = (User) userAsObjcet.get();
            long userId = user.getId();
            orderService.addOrder(serviceId, userId);
        }
        return requestFactory.createRedirectResponse("/controller?command=main");
    }
}
