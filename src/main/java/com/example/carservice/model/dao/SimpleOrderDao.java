package com.example.carservice.model.dao;

import com.example.carservice.entity.Order;
import com.example.carservice.entity.Role;
import com.example.carservice.entity.Service;
import com.example.carservice.entity.User;
import com.example.carservice.exception.DaoException;
import com.example.carservice.model.connection.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleOrderDao implements OrderDao{
    private static final Logger LOG = LoggerFactory.getLogger(SimpleOrderDao.class);
    private static final String SQL_FIND_ALL_ORDERS = "select order_id, s.service_id, s.service_name, s.service_cost,u.user_id, u.user_email, u.user_password, r.role_name , order_is_finished from`order`" +
            " left join service s on `order`.order_service_id = s.service_id" +
            " left join user u on `order`.order_user_id = u.user_id" +
            " left join role r on u.user_role = r.role_id";
    private static final String SQL_FIND_ORDERS_BY_USER_ID = "select order_id, s.service_id, s.service_name, s.service_cost,u.user_id, u.user_email, u.user_password, r.role_name , order_is_finished from `order`" +
            " left join service s on `order`.order_service_id = s.service_id" +
            " left join user u on `order`.order_user_id = u.user_id" +
            " left join role r on u.user_role = r.role_id" +
            " where user_id = ?";
    private static final String SQL_CREATE_ORDER = "insert into `order`(order_service_id, order_user_id,order_is_finished) values(?,?,?)";
    private static final String SQL_DELETE_ORDER = "delete from order where id = ?";
    private final ConnectionPool connectionPool;

    public SimpleOrderDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Order> findAllOrders() throws DaoException {
        List<Order> orders = new ArrayList<>();
        try(final Connection connection = connectionPool.getConnection(); final Statement statement = connection.createStatement()){
            final ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_ORDERS);
            while (resultSet.next()){
                final Order order = new Order.Builder().
                        withId(resultSet.getLong(1)).
                        withService(new Service.Builder().
                                withId(resultSet.getLong(2)).
                                withName(resultSet.getString(3)).
                                withCost(resultSet.getInt(4)).
                                build()).
                        withUser(new User.Builder().
                                withUserId(resultSet.getLong(5)).
                                withUserLogin(resultSet.getString(6)).
                                withUserPassword(resultSet.getString(7)).
                                withUserRole(Role.valueOf(resultSet.getString(8))).
                                build()).
                        withIsFinished(resultSet.getBoolean(9)).
                        build();
                orders.add(order);
            }
        }catch (SQLException e){
            LOG.error("Cannot find all orders",e);
            throw new DaoException("Cannot find all orders",e);
        }
        return orders;
    }

    @Override
    public boolean deleteOrderById(long orderId) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); final  PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER)){
            preparedStatement.setLong(1,orderId);
            final int countRowsDeleted = preparedStatement.executeUpdate();
            return countRowsDeleted>0;
        }catch (SQLException e){
            LOG.error("Cannot delete order by id",e);
            throw new DaoException("Cannot delete order by id",e);
        }
    }

    @Override
    public List<Order> findOrdersByUserId(long userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try(final Connection connection = connectionPool.getConnection(); final  PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ORDERS_BY_USER_ID)){
            preparedStatement.setLong(1,userId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                final Order order = new Order.Builder().
                        withId(resultSet.getLong(1)).
                        withService(new Service.Builder().
                                withId(resultSet.getLong(2)).
                                withName(resultSet.getString(3)).
                                withCost(resultSet.getInt(4)).
                                build()).
                        withUser(new User.Builder().
                                withUserId(resultSet.getLong(5)).
                                withUserLogin(resultSet.getString(6)).
                                withUserPassword(resultSet.getString(7)).
                                withUserRole(Role.valueOf(resultSet.getString(8))).
                                build()).
                        withIsFinished(resultSet.getBoolean(9)).
                        build();
                orders.add(order);
            }
        }catch (SQLException e){
            LOG.error("Cannot find order by id",e);
            throw new DaoException("Cannot find order by id",e);
        }
        return orders;
    }

    @Override
    public boolean addOrder(long orderId, long userId) throws DaoException {
        try(final  Connection connection = connectionPool.getConnection();final  PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER)){
            preparedStatement.setLong(1,orderId);
            preparedStatement.setLong(2,userId);
            preparedStatement.setBoolean(3,false);
            int countRowsUpdated = preparedStatement.executeUpdate();
            return countRowsUpdated>0;
        }catch (SQLException e){
            LOG.error("Cannot and order",e);
            throw new DaoException("Cannot and order",e);
        }
    }
}
