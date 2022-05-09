package com.example.carservice.model.service;

import com.example.carservice.entity.Service;
import com.example.carservice.exception.ServiceError;

import java.util.List;

public interface CarServiceService {
    List<Service> getAllServices()throws ServiceError;

    boolean addService(String serviceName, int serviceCost) throws ServiceError;

    boolean updateService(long serviceId, String serviceName, int serviceCost) throws ServiceError;

    boolean deleteService(long serviceId) throws ServiceError;
}
