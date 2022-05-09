package com.example.carservice.command;

import com.example.carservice.controller.RequestFactory;
import com.example.carservice.exception.ServiceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutCommand implements Command{
    private static final Logger LOG = LoggerFactory.getLogger(LogoutCommand.class);
    private final RequestFactory requestFactory;

    public LogoutCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (noLoggedInUserPresent(request)) {
            return requestFactory.createForwardResponse(PagePath.MAIN_PAGE.getPath());
        }
        request.clearSession();
        return requestFactory.createRedirectResponse("/controller?command=main");
    }

    private boolean noLoggedInUserPresent(CommandRequest request) {
        return !request.sessionExists() || (
                request.sessionExists()
                        && !request.retrieveFromSession("user")
                        .isPresent()
        );
    }
}
