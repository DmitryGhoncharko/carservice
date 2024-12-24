package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Test;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.AnswerDao;
import com.example.carservice.model.dao.QuestionDao;
import com.example.carservice.model.dao.TestDao;
import com.example.carservice.model.dao.TestDataDao;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DeleteTestCommand implements Command {
    private final RequestFactory requestFactory;
    private final TestDao testDao;
    private final TestDataDao testDataDao;

    @Override
    public CommandResponse execute(CommandRequest request) {
        Long id = Long.valueOf(request.getParameter("id"));
        try {
            Optional<Test> testOptional = testDao.getTestById(id);
            if(testOptional.isPresent()) {
                Test test = testOptional.get();
                testDao.deleteTestById(test.getId());
                testDataDao.deleteByTestId(test.getId());
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return requestFactory.createRedirectResponse("/controller?command=listTests");
    }
}
