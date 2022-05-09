package com.example.carservice.model.dao;

import com.example.carservice.entity.Service;
import com.example.carservice.exception.DaoException;

import java.util.List;

public interface CarServiceDao {
    List<Service> getAllServices() throws DaoException;

    boolean addService(String serviceName, int serviceCost) throws DaoException;

    boolean updateService(long serviceId, String serviceName, int serviceCost) throws DaoException;

    boolean deleteService(long serviceId) throws DaoException;
}
