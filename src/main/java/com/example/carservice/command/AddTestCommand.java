package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.TestDao;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddTestCommand implements Command{
    private final RequestFactory requestFactory;
    private final TestDao testDao;
    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        String testName = request.getParameter("testName");
        try {
            testDao.addTest(testName);
        } catch (DaoException e) {

        }
        return requestFactory.createRedirectResponse("/controller?command=addTestPage");
    }
}
