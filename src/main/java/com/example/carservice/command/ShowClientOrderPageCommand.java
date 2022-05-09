package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Order;
import com.example.carservice.entity.User;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.service.OrderService;

import java.util.List;
import java.util.Optional;

public class ShowClientOrderPageCommand implements Command{
    private final RequestFactory requestFactory;
    private final OrderService orderService;
    public ShowClientOrderPageCommand(RequestFactory requestFactory, OrderService orderService) {
        this.requestFactory = requestFactory;
        this.orderService = orderService;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        Optional<Object> userAsObject = request.retrieveFromSession("user");
        if(userAsObject.isPresent()){
            final User user =(User) userAsObject.get();
            final List<Order> orders = orderService.findOrdersByUserId(user.getId());
            request.addAttributeToJsp("orders",orders);
        }
        return requestFactory.createForwardResponse(PagePath.CLIENT_ORDERS_PAGE.getPath());
    }
}
