package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Question;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.QuestionDao;
import com.example.carservice.model.dao.TestDataDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteQuestionCommand implements Command {
    private final RequestFactory requestFactory;
    private final QuestionDao questionDao;
    private final TestDataDao testDataDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long questionId = Long.parseLong(request.getQueryString().split("\\&")[1].replace("id=",""));
        try {
            testDataDao.updateByQuestionIdToNull(questionId);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        try {
            questionDao.removeQuestion(questionId);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return requestFactory.createRedirectResponse("/controller?command=listTests");
    }
}
