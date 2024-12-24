package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.entity.Test;
import com.example.carservice.exception.DaoException;
import com.example.carservice.exception.ServiceError;
import com.example.carservice.model.dao.TestDao;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class EditTestCommandPage implements Command {
    private final TestDao testDao;
    private final RequestFactory requestFactory;

    @Override
    public CommandResponse execute(CommandRequest request) throws ServiceError {
        long testId = Long.parseLong(request.getQueryString().split("\\?")[1].replace("id=",""));
        Optional<Test> testOptional = null;
        try {
            testOptional = testDao.getTestById(testId);
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        if (testOptional.isPresent()) {
            request.addAttributeToJsp("test",testOptional.get());
            return requestFactory.createForwardResponse(PagePath.UPDATE_TEST_PAGE.getPath());
        }
        return requestFactory.createForwardResponse(PagePath.MAIN_PAGE.getPath());
    }
}
