package com.example.carservice.model.service;

import com.example.carservice.entity.Order;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;

import java.util.List;

public interface OrderService {
    List<Order> findAllOrder()throws ServiceError;
    boolean deleteOrderById(long orderId) throws ServiceError;
    List<Order> findOrdersByUserId(long userId) throws ServiceError;
    boolean addOrder(long orderId, long userId) throws ServiceError;
}
