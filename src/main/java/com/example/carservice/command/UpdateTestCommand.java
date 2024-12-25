package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Test;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.TestDao;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateTestCommand implements Command {
    private final TestDao testDao;
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long testId = Long.parseLong(request.getParameter("testId"));
        String testName = request.getParameter("testName");

        try {
            Optional<Test> testOptional = null;
            try {
                testOptional = testDao.getTestById(testId);
            } catch (DaoException e) {
                throw new RuntimeException(e);
            }
            if (testOptional.isPresent()) {
                Test test = testOptional.get();
                test.setName(testName);
                testDao.updateTestById(testId,testName);
            }
        } catch (DaoException e) {
            throw new RuntimeException("Error updating test", e);
        }
        return requestFactory.createRedirectResponse("/controller?command=listTests");
    }

}
