package com.example.carservice.model.dao;

import com.example.carservice.entity.Role;
import com.example.carservice.entity.User;
import com.example.carservice.exception.DaoException;
import com.example.carservice.model.connection.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SimpleUserDao implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserDao.class);
    private static final String SQL_AUTHENTICATE_USER = "select user_id, user_email, r.role_name from user" +
            " left join role r on r.role_id = user.user_role" +
            " where user_email = ?";
    private final ConnectionPool connectionPool;

    public SimpleUserDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_AUTHENTICATE_USER)) {
            preparedStatement.setString(1, login);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User.Builder().
                        withUserId(resultSet.getLong(1)).
                        withUserLogin(resultSet.getString(2)).
                        withUserRole(Role.valueOf(resultSet.getString(3))).
                        build());
            }
        } catch (SQLException e) {
            LOG.error("Cannot find user by user login, user login : " + login, e);
            throw new DaoException("Cannot find user by user login, user login : " + login, e);
        }
        LOG.error("Cannot find user by userLogin, user login : " + login);
        return Optional.empty();
    }
}
