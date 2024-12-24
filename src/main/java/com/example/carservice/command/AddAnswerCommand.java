package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Answer;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.AnswerDao;
import com.example.carservice.model.dao.QuestionDao;
import com.example.carservice.model.dao.TestDataDao;
import com.example.carservice.model.dao.UserDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddAnswerCommand implements Command {
    private final RequestFactory requestFactory;
    private final AnswerDao answerDao;
    private final QuestionDao questionDao;
    private final TestDataDao testDataDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long questionId = Long.parseLong(request.getParameter("questionId"));
        String answer = request.getParameter("answer");
        boolean correct = "on".equals(request.getParameter("correct"));
        Answer answer1 = null;
        try {
            answer1 = answerDao.addAnswer(answer,correct);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        long testId = 0;
        try {
            testId = testDataDao.getTestIdByQuestionId(questionId);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        try {
            testDataDao.save(testId,questionId,answer1.getId());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        return requestFactory.createRedirectResponse("/controller?command=main");
    }
}
