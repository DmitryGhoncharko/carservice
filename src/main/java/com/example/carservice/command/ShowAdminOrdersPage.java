package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Order;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.OrderService;

import java.util.List;

public class ShowAdminOrdersPage implements Command{
    private final RequestFactory requestFactory;
    private final OrderService orderService;

    public ShowAdminOrdersPage(RequestFactory requestFactory, OrderService orderService) {
        this.requestFactory = requestFactory;
        this.orderService = orderService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        final List<Order> orders = orderService.findAllOrder();
        request.addAttributeToJsp("orders",orders);
        return requestFactory.createForwardResponse(PagePath.ORDERS_PAGE.getPath());
    }
}
