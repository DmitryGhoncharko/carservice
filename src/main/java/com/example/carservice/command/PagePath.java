package com.example.carservice.command;

public enum PagePath {
    MAIN_PAGE("/WEB-INF/jsp/main.jsp"), LOGIN_PAGE("/WEB-INF/jsp/login.jsp"),
    REGISTRATION_PAGE("/WEB-INF/jsp/registration.jsp"),
    ADD_TEST_PAGE("/WEB-INF/jsp/addTest.jsp"),
    LIST_TEST_PAGE("/WEB-INF/jsp/listTests.jsp"),
    UPDATE_TEST_PAGE("/WEB-INF/jsp/updateTest.jsp"),
    QUESTIONS_PAGE("/WEB-INF/jsp/questions.jsp"),
    QUESTION_ADD_PAGE("/WEB-INF/jsp/questionAdd.jsp"),
    ANSWER_ADD_PAGE("/WEB-INF/jsp/addAnswer.jsp"),
    TESTS_PAGE_FOR_CLIENT("/WEB-INF/jsp/tests.jsp"),
    START_TEST_FOR_CLIENT("/WEB-INF/jsp/startTest.jsp"),;
    private final String path;

    PagePath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
