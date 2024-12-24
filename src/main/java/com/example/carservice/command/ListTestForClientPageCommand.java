package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Test;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.TestDao;
import com.example.carservice.model.dao.TestDataDao;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListTestForClientPageCommand implements Command {
    private final RequestFactory requestFactory;
    private final TestDataDao testDataDao;
    private final TestDao testDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        List<Test> tests = null;
        try {
            tests = testDao.getAllTests();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        request.addAttributeToJsp("tests",tests);
        return requestFactory.createForwardResponse(PagePath.TESTS_PAGE_FOR_CLIENT.getPath());
    }
}
