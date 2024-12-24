package com.example.carservice.model.dao;

import com.example.carservice.entity.Role;
import com.example.carservice.entity.User;
import com.example.carservice.exception.DaoException;
import com.example.carservice.model.connection.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Optional;

public class SimpleUserDao implements UserDao {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleUserDao.class);
    private static final String SQL_AUTHENTICATE_USER = "select user_id, user_login, user_password, r.user_role_name from test._user" +
            " left join test.user_role r on r.user_role_id = test._user.user_role_id" +
            " where user_login = ?";
    private static final String SQL_ADD_USER = "insert into test._user(user_login, user_password, user_role_id) VALUES (?,?,?)";
    private final ConnectionPool connectionPool;

    public SimpleUserDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_AUTHENTICATE_USER)) {
            preparedStatement.setString(1, login);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User.Builder().
                        withUserId(resultSet.getLong(1)).
                        withUserLogin(resultSet.getString(2)).
                        withUserPassword(resultSet.getString(3)).
                        withUserRole(Role.valueOf(resultSet.getString(4))).
                        build());
            }
        } catch (SQLException e) {
            LOG.error("Cannot find user by user login, user login : " + login, e);
            throw new DaoException("Cannot find user by user login, user login : " + login, e);
        }
        LOG.error("Cannot find user by userLogin, user login : " + login);
        return Optional.empty();
    }

    @Override
    public boolean addUser(String login, String password, Role role) throws DaoException {
        try (final Connection connection = connectionPool.getConnection(); final PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 3);
            final int countRowsAdded = preparedStatement.executeUpdate();
            if (countRowsAdded > 0) {
                return true;
            }

        } catch (SQLException e) {
            LOG.error("Cannot add user, userLogin: " + login, e);
            throw new DaoException("Cannot add user, userLogin: " + login, e);
        }
        LOG.info("Cannot add user, userLogin: " + login);
        return false;
    }
}
