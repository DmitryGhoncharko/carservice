package com.example.carservice.model.dao;

import com.example.carservice.entity.User;
import com.example.carservice.exception.DaoException;

import java.util.Optional;

public interface UserDao {
    Optional<User> findUserByLogin(String login) throws DaoException;
}
