package thirdparty.service;

public class BudgetIsOverException extends Exception {
    public BudgetIsOverException(String message) {
        super(message);
    }
}
