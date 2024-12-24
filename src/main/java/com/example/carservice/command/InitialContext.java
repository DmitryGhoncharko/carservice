package com.example.carservice.command;


import com.example.carservice.controller.RequestFactory;
import com.example.carservice.controller.SimpleRequestFactory;
import com.example.carservice.model.connection.ConnectionPool;
import com.example.carservice.model.connection.HikariCPConnectionPool;
import com.example.carservice.model.dao.*;
import com.example.carservice.model.service.*;
import com.example.carservice.securiy.BcryptWithSaltHasherImpl;
import com.example.carservice.securiy.PasswordHasher;
import com.example.carservice.validator.SimpleUserValidator;
import com.example.carservice.validator.UserValidator;

public class InitialContext {
    private final ConnectionPool hikariCPConnectionPool = new HikariCPConnectionPool();
    private final PasswordHasher bcryptWithSaltHasher = new BcryptWithSaltHasherImpl();
    private final UserValidator userValidator = new SimpleUserValidator();
    private final UserDao simpleUserDao = new SimpleUserDao(hikariCPConnectionPool);
    private final UserService simpleUserService = new SimpleUserService(simpleUserDao, userValidator, bcryptWithSaltHasher);
    private final RequestFactory simpleRequestFactory = new SimpleRequestFactory();
    private final TestDao testDao = new TestDao(hikariCPConnectionPool);
    private final QuestionDao questionDao = new QuestionDao(hikariCPConnectionPool);
    private final AnswerDao answerDao = new AnswerDao(hikariCPConnectionPool);
    private final TestDataDao testDataDao = new TestDataDao(hikariCPConnectionPool);

    public Command lookup(String commandName) {

        switch (commandName) {
            case "login":
                return new ShowLoginPageCommand(simpleRequestFactory);
            case "logincmnd":
                return new LoginCommand(simpleRequestFactory, simpleUserService);
            case "registration":
                return new ShowRegistrationPage(simpleRequestFactory);
            case "registrationcmnd":
                return new RegistrationCommand(simpleRequestFactory, simpleUserService);
            case "addTestPage":
                return new AddTestPageCommand(simpleRequestFactory);
            case "addTest":
                return new AddTestCommand(simpleRequestFactory, testDao);
            case "listTests":
                return new ListTestPageCommand(simpleRequestFactory, testDao);
            case "deleteTest":
                return new DeleteTestCommand(simpleRequestFactory, testDao, testDataDao);
            case "editTest":
                return new EditTestCommandPage(testDao, simpleRequestFactory);
            case "updateTest":
                return new UpdateTestCommand(testDao, simpleRequestFactory);
            case "viewQuestions":
                return new ShowQuestionsPageCommand(simpleRequestFactory,testDataDao,questionDao,testDao);
            case "deleteQuestion":
                return new DeleteQuestionCommand(simpleRequestFactory,questionDao,testDataDao);
            case "addQuestion":
                return new AddQuestonPageCommand(simpleRequestFactory);
            case"addQuestion1":
                return new AddQuestionCommand(simpleRequestFactory,questionDao,testDataDao);
            case "editQuestion":
                return new AddAnswerPageCommand(simpleRequestFactory,questionDao,answerDao,testDataDao);
            case "addAnswerc":
                return new AddAnswerCommand(simpleRequestFactory,answerDao,questionDao,testDataDao);
            case "listTestsC":
                return new ListTestForClientPageCommand(simpleRequestFactory,testDataDao,testDao);
            case "startTest":
                return new StartTestPageCommand(simpleRequestFactory,testDataDao,questionDao,testDao,answerDao);
            case "logout":
                return new LogoutCommand(simpleRequestFactory);
            default:
                return new ShowMainPageCommand(simpleRequestFactory);
        }

    }
}
