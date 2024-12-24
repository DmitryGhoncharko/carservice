package com.example.carservice.model.dao;

import com.example.carservice.entity.TestData;
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
public class TestDataDao {
    private final ConnectionPool connectionPool;
    public Optional<TestData> findById(long id) {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select test_data_id, test_id, question_id, answer_id from test._test_data")){

        }catch (SQLException e){
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    public TestData save( Long testId, Long questionId, Long answerId) throws DaoException {
        try(Connection connection = connectionPool.getConnection();PreparedStatement preparedStatement = connection.prepareStatement("insert into test._tests_data(test_id,question_id,answer_id) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setLong(1, testId);
            preparedStatement.setLong(2, questionId);
            if(answerId != null){
                preparedStatement.setLong(3, answerId);
            }else {
                preparedStatement.setNull(3, Types.NULL);
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                return new TestData(resultSet.getLong(1), testId, questionId, answerId);
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
        throw new DaoException();
    }
    public void deleteByTestId(Long testId) throws DaoException {
        String query = "DELETE FROM test._tests_data WHERE test_id = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, testId);
            int rowsAffected = preparedStatement.executeUpdate();
            log.info("Deleted " + rowsAffected + " rows from _tests_data for test_id = " + testId);
        } catch (SQLException e) {
            log.error("Error deleting rows for test_id = " + testId, e);
            throw new DaoException("Failed to delete rows from _tests_data", e);
        }
    }
    public List<TestData> findByTestId(Long testId) throws DaoException {
        String query = "SELECT test_data_id, test_id, question_id, answer_id FROM test._tests_data WHERE test_id = ?";
        List<TestData> testDataList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, testId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Long testDataId = resultSet.getLong("test_data_id");
                    Long questionId = resultSet.getLong("question_id");
                    Long answerId = resultSet.getLong("answer_id");
                    testDataList.add(new TestData(testDataId, testId, questionId, answerId));
                }
            }
        } catch (SQLException e) {
            log.error("Error finding rows for test_id = " + testId, e);
            throw new DaoException("Failed to find rows from _tests_data", e);
        }
        return testDataList;
    }

    public void updateByQuestionIdToNull(Long oldQuestionId) throws DaoException {
        String query = "UPDATE test._tests_data SET question_id = NULL, answer_id = NULL WHERE question_id = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, oldQuestionId);

            int rowsAffected = preparedStatement.executeUpdate();
            log.info("Updated " + rowsAffected + " rows in _tests_data where question_id = " + oldQuestionId);
        } catch (SQLException e) {
            log.error("Error updating rows for question_id = " + oldQuestionId, e);
            throw new DaoException("Failed to update rows in _tests_data", e);
        }
    }
    public Long getTestIdByQuestionId(Long questionId) throws DaoException {
        String query = "SELECT test_id FROM test._tests_data WHERE question_id = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, questionId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Если запись найдена, возвращаем test_id
                    return resultSet.getLong("test_id");
                } else {
                    // Если записи нет для данного question_id, можно вернуть null
                    return null;
                }
            }
        } catch (SQLException e) {
            log.error("Error fetching test_id for question_id = " + questionId, e);
            throw new DaoException("Failed to fetch test_id from _tests_data", e);
        }
    }

}
