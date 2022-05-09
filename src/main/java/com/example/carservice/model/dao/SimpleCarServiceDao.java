package com.example.carservice.model.dao;

import com.example.carservice.entity.Service;
import com.example.carservice.exception.DaoException;
import com.example.carservice.model.connection.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleCarServiceDao implements CarServiceDao {
    private static final Logger LOG  = LoggerFactory.getLogger(SimpleCarServiceDao.class);
    private static final String SQL_FIND_ALL_SERVICES = "select service_id, service_name, service_cost from service";
    private static final String SQL_ADD_SERVICE = "insert into service(service_name,service_cost) values(?,?)";
    private static final String SQL_UPDATE_SERVICE = "update service set service_name = ?, service_cost=? where service_id = ?";
    private static final String SQL_DELETE_SERVICE = "delete from service where service_id = ?";
    private final ConnectionPool connectionPool;

    public SimpleCarServiceDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Service> getAllServices() throws DaoException {
        List<Service> serviceList = new ArrayList<>();
        try(final Connection connection = connectionPool.getConnection(); final Statement statement = connection.createStatement()){
            final ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL_SERVICES);
            while (resultSet.next()){
                final Service service = new Service.Builder().withId(resultSet.getLong(1)).
                        withName(resultSet.getString(2)).
                        withCost(resultSet.getInt(3)).
                        build();
                serviceList.add(service);
            }
        }catch (SQLException e){
            LOG.error("Cannot find all services",e);
            throw new DaoException("Cannot find all services",e);
        }
        return serviceList;
    }

    @Override
    public boolean addService(String serviceName, int serviceCost) throws DaoException {
        try(final Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_SERVICE)){
            preparedStatement.setString(1,serviceName);
            preparedStatement.setInt(2,serviceCost);
           final int countRowsAdded = preparedStatement.executeUpdate();
            return countRowsAdded>0;
        }catch (SQLException e){
            LOG.error("Cannot add new service, service name: " + serviceName, e);
            throw new DaoException("Cannot add new service, service name: " + serviceName, e);
        }
    }

    @Override
    public boolean updateService(long serviceId, String serviceName, int serviceCost) throws DaoException {
        try(final Connection connection = connectionPool.getConnection(); final  PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SERVICE)){
            preparedStatement.setString(1,serviceName);
            preparedStatement.setInt(2,serviceCost);
            preparedStatement.setLong(2,serviceId);
            int countRowsUpdated = preparedStatement.executeUpdate();
            return countRowsUpdated>0;
        }catch (SQLException e){
            LOG.error("Cannot update service, serviceName: " + serviceName);
            throw new DaoException("Cannot update service, serviceName: " + serviceName);
        }
    }

    @Override
    public boolean deleteService(long serviceId) throws DaoException {
        try(final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_SERVICE)){
            preparedStatement.setLong(1,serviceId);
            int countRowsDeleted = preparedStatement.executeUpdate();
            return countRowsDeleted>0;
        }catch (SQLException e){
            LOG.error("Cannot delete service",e);
            throw new DaoException("Cannot delete service",e);
        }
    }
}
