package com.example.carservice.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleUserValidator implements UserValidator {

    private static final String LOGIN_REGEXP = "^[a-zA-Z0-9]{1,50}@[a-zA-Z0-9]{1,50}.[a-zA-Z0-9]{1,20}$";

    private static final String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,200}$";

//    private static final String SECRET_KEY_REGEXP = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{10,100}$";

    @Override
    public boolean validateUserDataByLoginAndPassword(String login, String password) {
       return true;
    }

    @Override
    public boolean validateUserDataByLoginAndPasswordWithSecretKey(String login, String password, String secretKey) {
       return true;

    }
}
