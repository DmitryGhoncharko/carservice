package com.example.carservice.model.dao;

import com.example.carservice.entity.Answer;
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
public class AnswerDao {
    private final ConnectionPool connectionPool;

    public Answer addAnswer(String answerText, boolean correct) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("insert into test._answer(answer_text, answer_true) values(?,?)", Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, answerText);
            preparedStatement.setBoolean(2, correct);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                long id = resultSet.getLong(1);
                return new Answer(id,answerText,correct);
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
        throw new DaoException();
    }
    public Optional<Answer> getAnswer(long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("select answer_text, answer_true from test._answer where answer_id = ?")){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String answerText = resultSet.getString(1);
                boolean correct = resultSet.getBoolean(2);
                return Optional.of(new Answer(id,answerText,correct));
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
        return Optional.empty();
    }
    public List<Answer> getAllAnswers() throws DaoException {
        List<Answer> answers = new ArrayList<>();
        try(Connection connection = connectionPool.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("select answer_id, answer_text, answer_true from test._answer");
            while(resultSet.next()){
                long id = resultSet.getLong(1);
                String answerText = resultSet.getString(2);
                boolean correct = resultSet.getBoolean(3);
                answers.add(new Answer(id,answerText,correct));
            }
        }catch (SQLException e){
            log.error(e.getMessage());
        }
        return answers;
    }
    public void removeAnswer(long id) throws DaoException {
        try(Connection connection = connectionPool.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("delete from test._answer where answer_id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            log.error(e.getMessage());
        }
    }
}
