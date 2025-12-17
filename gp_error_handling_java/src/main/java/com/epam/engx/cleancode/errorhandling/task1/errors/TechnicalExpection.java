package com.epam.engx.cleancode.errorhandling.task1.errors;

public abstract class TechnicalExpection extends RuntimeException {
    public TechnicalExpection(String message) {
        super(message);
    }
}
