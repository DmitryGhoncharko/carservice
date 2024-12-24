package com.example.carservice.model.dao;

import com.example.carservice.entity.Question;
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
public class QuestionDao {
    private final ConnectionPool connectionPool;

    public Question addQuestion(String question) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("insert into test._question(question_text) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, question);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                return new Question(id, question);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        throw new DaoException();
    }

    public List<Question> getAllQuestions() throws DaoException {
        List<Question> questions = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from test._question");
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String question = resultSet.getString(2);
                questions.add(new Question(id, question));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return questions;
    }

    public Optional<Question> getQuestion(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select qusestion_id, question_text from test._question where qusestion_id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                long questionId = resultSet.getLong(1);
                String questionText = resultSet.getString(2);
                return Optional.of(new Question(questionId, questionText));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return Optional.empty();
    }

    public void removeQuestion(long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from test._question where question_id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

}
