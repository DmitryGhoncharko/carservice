package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.OrderService;

public class SubmitOrderCommand implements Command{
    private final RequestFactory requestFactory;
    private final OrderService orderService;

    public SubmitOrderCommand(RequestFactory requestFactory, OrderService orderService) {
        this.requestFactory = requestFactory;
        this.orderService = orderService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long orderId = Long.parseLong(request.getParameter("orderId"));
        orderService.deleteOrderById(orderId);
        return requestFactory.createRedirectResponse("/controller?command=showadminorders");
    }
}
