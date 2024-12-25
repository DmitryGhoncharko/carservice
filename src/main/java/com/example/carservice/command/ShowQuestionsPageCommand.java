package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Question;
import com.example.carservice.entity.TestData;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.QuestionDao;
import com.example.carservice.model.dao.TestDao;
import com.example.carservice.model.dao.TestDataDao;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ShowQuestionsPageCommand implements Command {
    private final RequestFactory requestFactory;
    private final TestDataDao testDataDao;
    private final QuestionDao questionDao;
    private final TestDao testDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long testId = Long.parseLong(request.getQueryString().split("\\?")[1].replace("id=",""));
        List<TestData> testDataList = null;
        try {
            testDataList = testDataDao.findByTestId(testId);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        List<Question> questionList = null;
        try {
            questionList = questionDao.getAllQuestions();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        List<Question> result = new ArrayList<>();
        for(Question question : questionList) {
            for(TestData testData : testDataList) {
                if(question.getId().equals(testData.getQuestionId())) {
                    if(!result.contains(question)) {
                        result.add(question);
                    }
                }
            }
        }

        request.addAttributeToJsp("testId",testId);
        request.addAttributeToJsp("questions",result);
        return requestFactory.createForwardResponse(PagePath.QUESTIONS_PAGE.getPath());
    }

}
