package thirdparty.service;

public class InsufficientBudgetException extends Exception {
    public InsufficientBudgetException(String message) {
        super(message);
    }
}
