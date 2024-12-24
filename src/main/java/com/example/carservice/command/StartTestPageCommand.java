package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Answer;
import com.example.carservice.entity.Question;
import com.example.carservice.entity.Test;
import com.example.carservice.entity.TestData;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.AnswerDao;
import com.example.carservice.model.dao.QuestionDao;
import com.example.carservice.model.dao.TestDao;
import com.example.carservice.model.dao.TestDataDao;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class StartTestPageCommand implements Command {
    private final RequestFactory requestFactory;
    private final TestDataDao testDataDao;
    private final QuestionDao questionDao;
    private final TestDao testDao;
    private final AnswerDao answerDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long testId = Long.parseLong(request.getQueryString().split("\\&")[1].replace("id=",""));
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
        try {
            List<Test> testList = testDao.getAllTests();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        List<Answer> dataList = null;
        try {
            dataList = answerDao.getAllAnswers();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        Map<String,List<Answer>> map = new HashMap<>();
        for(TestData testData : testDataList) {
            String questionText = null;
            List<Answer> answerList = new ArrayList<>();
            for(Question question : questionList) {
                if(testData.getQuestionId().equals(question.getId())) {
                    questionText = question.getQuestion();
                }
            }
            List<TestData> dataList1 = testDataList.stream().filter(x->x.getQuestionId().equals(testData.getQuestionId())).collect(Collectors.toList());
            for(TestData testData1 : dataList1) {
                for(Answer answer : dataList) {
                    if(testData1.getAnswerId().equals(answer.getId())) {
                        answerList.add(answer);
                    }
                }
            }
            if(!map.containsKey(questionText)) {
                map.put(questionText, answerList);
            }
            request.addAttributeToJsp("data",map);
        }
        return requestFactory.createForwardResponse(PagePath.START_TEST_FOR_CLIENT.getPath());
    }
}
