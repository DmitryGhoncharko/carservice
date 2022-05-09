package com.example.carservice.model.service;

import com.example.carservice.entity.Order;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SimpleOrderService implements OrderService{
    private static final Logger LOG = LoggerFactory.getLogger(SimpleCarServiceService.class);
    private final OrderDao orderDao;

    public SimpleOrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findAllOrder() throws ServiceError {
        try{
            return orderDao.findAllOrders();
        }catch (DaoException e){
            LOG.error("Cannot find all orders",e);
            throw new ServiceError("Cannot find all orders",e);
        }
    }

    @Override
    public boolean deleteOrderById(long orderId) throws ServiceError {
        try{
            return orderDao.deleteOrderById(orderId);
        }catch (DaoException e){
            LOG.error("Cannot delete order by id",e);
            throw new ServiceError("Cannot delete order by id",e);
        }
    }

    @Override
    public List<Order> findOrdersByUserId(long userId) throws ServiceError {
        try{
            return orderDao.findOrdersByUserId(userId);
        }catch (DaoException e){
            LOG.error("Cannot find orders by user id",e);
            throw new ServiceError("Cannot find orders by user id",e);
        }
    }

    @Override
    public boolean addOrder(long orderId, long userId) throws ServiceError {
        try{
            return orderDao.addOrder(orderId, userId);
        }catch (DaoException e){
            LOG.error("Cannot add order",e);
            throw new ServiceError("Cannot and order",e);
        }
    }
}
