package com.example.carservice.model.service;

import com.example.carservice.entity.Role;
import com.example.carservice.entity.User;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.UserDao;
import com.example.carservice.securiy.PasswordHasher;
import com.example.carservice.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class SimpleUserService implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserService.class);
    private final UserDao userDao;
    private final UserValidator userValidator;
    private final PasswordHasher passwordHasher;

    public SimpleUserService(UserDao userDao, UserValidator userValidator, PasswordHasher passwordHasher) {
        this.userDao = userDao;
        this.userValidator = userValidator;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public Optional<User> authenticateUser(String login, String password) throws ServiceError {
        if (!userValidator.validateUserDataByLoginAndPassword(login, password)) {
            LOG.info("Invalid user data, login: " + login + " password: " + password);
            return Optional.empty();
        }
        try {
            final Optional<User> userFromDB = userDao.findUserByLogin(login);
            if (userFromDB.isPresent()) {
                final User userInstance = userFromDB.get();
                final String hashedPasswordFromDB = userInstance.getPassword();
                if (passwordHasher.checkIsEqualsPasswordAndPasswordHash(password, hashedPasswordFromDB)) {
                    return userFromDB;
                }
            }
        } catch (DaoException e) {
            LOG.error("Cannot authentucate user, userLogin: " + login + " userPassword: " + password, e);
            throw new ServiceError("Cannot authentucate user, userLogin: " + login + " userPassword: " + password, e);
        }
        return Optional.empty();
    }

    @Override
    public boolean addUserAsClient(String login, String password) throws ServiceError {
        if (!userValidator.validateUserDataByLoginAndPassword(login, password)) {
            return false;
        }
        try {
            String hashedPassword = passwordHasher.hashPassword(password);
            return userDao.addUser(login, hashedPassword, Role.CLIENT);
        } catch (DaoException e) {
            LOG.error("Cannot add user as client, userLogin: " + login, e);
            throw new ServiceError("Cannot add user as client, userLogin: " + login, e);
        }
    }
}
