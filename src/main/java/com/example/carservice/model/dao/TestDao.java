package com.example.carservice.model.dao;

import com.example.carservice.entity.Test;
import com.example.carservice.exception.DaoException;
import com.example.carservice.model.connection.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class TestDao {
    private final ConnectionPool connectionPool;

    public Test addTest(String testName) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("insert into test._test(test_name) values(?)", Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, testName);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                long id = resultSet.getLong(1);
                return new Test(id,testName);
            }
        }catch (SQLException e) {
            log.error(e.getMessage());
        }
        throw new DaoException();
    }
    public List<Test> getAllTests() throws DaoException {
        List<Test> tests = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select test_id, test_name from test._test");
            while(resultSet.next()){
                tests.add(new Test(resultSet.getLong(1),resultSet.getString(2)));
            }
        }catch (SQLException e) {
            log.error(e.getMessage());
        }
        return tests;
    }
    public Optional<Test> getTestById(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select test_name from test._test where test_id = ?")){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(new Test(id,resultSet.getString(1)));
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
    public void deleteTestById(long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from test._test where test_id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            log.error(e.getMessage());
        }
    }

    public void updateTestById(long id, String testName) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("update test._test set test_name = ? where test_id = ?")){
            preparedStatement.setString(1, testName);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            log.error(e.getMessage());
        }
    }
}
