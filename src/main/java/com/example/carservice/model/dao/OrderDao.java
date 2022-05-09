package com.example.carservice.model.dao;

import com.example.carservice.entity.Order;
import com.example.carservice.exception.DaoException;

import java.util.List;

public interface OrderDao {
    List<Order> findAllOrders() throws DaoException;

    boolean deleteOrderById(long orderId) throws DaoException;

    List<Order> findOrdersByUserId(long userId) throws DaoException;

    boolean addOrder(long orderId, long userId) throws DaoException;
}
