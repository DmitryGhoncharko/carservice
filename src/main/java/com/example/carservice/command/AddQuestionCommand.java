package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Question;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.QuestionDao;
import com.example.carservice.model.dao.TestDataDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddQuestionCommand implements Command {
    private final RequestFactory requestFactory;
    private final QuestionDao questionDao;
    private final TestDataDao testDataDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String testId = request.getParameter("testId");
        String questionText = request.getParameter("questionText");
        Question question = null;
        try {
            question = questionDao.addQuestion(questionText);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        try {
            testDataDao.save(Long.parseLong(testId),question.getId(),null);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return requestFactory.createRedirectResponse("/controller?command=listTests");
    }
}
