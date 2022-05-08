package com.example.carservice.model.service;

import com.example.carservice.entity.User;
import com.example.carservice.exception.ServiceError;

import java.util.Optional;

public interface UserService {
    Optional<User> authenticateUser(String login, String password) throws ServiceError;

}
