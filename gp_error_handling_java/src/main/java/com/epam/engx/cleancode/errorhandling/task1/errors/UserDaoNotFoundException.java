package com.epam.engx.cleancode.errorhandling.task1.errors;

public class UserDaoNotFoundException extends TechnicalExpection {
    public UserDaoNotFoundException(String message) {
        super(message);
    }
}
