package com.example.carservice.model.service;

import com.example.carservice.entity.Service;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.CarServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SimpleCarServiceService implements CarServiceService{
    private static final Logger LOG = LoggerFactory.getLogger(SimpleCarServiceService.class);
    private final CarServiceDao carServiceDao;

    public SimpleCarServiceService(CarServiceDao carServiceDao) {
        this.carServiceDao = carServiceDao;
    }

    @Override
    public List<Service> getAllServices() throws ServiceError {
        try{
            return carServiceDao.getAllServices();
        }catch (DaoException e){
            LOG.error("Cannot find all car services ",e);
            throw new ServiceError("Cannot find all car services ",e);
        }
    }

    @Override
    public boolean addService(String serviceName, int serviceCost) throws ServiceError {
        try{
          return carServiceDao.addService(serviceName,serviceCost);
        }catch (DaoException e){
            LOG.error("Cannot add new carserice, serviceName: " + serviceName, e);
            throw new ServiceError("Cannot add new carserice, serviceName: " + serviceName, e);
        }
    }

    @Override
    public boolean updateService(long serviceId, String serviceName, int serviceCost) throws ServiceError {
        try{
            return carServiceDao.updateService(serviceId,serviceName,serviceCost);
        }catch (DaoException e){
            LOG.error("cannot update service",e);
            throw new ServiceError("cannot update service",e);
        }
    }

    @Override
    public boolean deleteService(long serviceId) throws ServiceError {
        try{
            return carServiceDao.deleteService(serviceId);
        }catch (DaoException e){
            LOG.error("Cannot delete service",e);
            throw new ServiceError("Cannot delete service",e);
        }
    }
}
