package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Test;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.TestDao;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ListTestPageCommand implements Command {
    private final RequestFactory requestFactory;
    private final TestDao testDao;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        List<Test> testList = null;
        try {
            testList = testDao.getAllTests();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        request.addAttributeToJsp("tests",testList);
        return requestFactory.createForwardResponse(PagePath.LIST_TEST_PAGE.getPath());
    }
}
