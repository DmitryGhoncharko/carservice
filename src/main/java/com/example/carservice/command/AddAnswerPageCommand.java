package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Question;
import com.example.carservice.entity.TestData;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.AnswerDao;
import com.example.carservice.model.dao.QuestionDao;
import com.example.carservice.model.dao.TestDataDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddAnswerPageCommand implements Command {
    private final RequestFactory requestFactory;
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;
    private final TestDataDao testDataDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long questionId = Long.parseLong(request.getQueryString().split("\\&")[1].replace("id=",""));
        Question question = null;
        try {
            question = questionDao.getQuestion(questionId).get();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        request.addAttributeToJsp("question",question);
        return requestFactory.createForwardResponse(PagePath.ANSWER_ADD_PAGE.getPath());
    }
}
