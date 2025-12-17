package com.epam.engx.cleancode.errorhandling.task1.errors;

public abstract class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
