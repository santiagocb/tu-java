package thirdparty.service;

public class NoSuchProfessionException extends RuntimeException {
    public NoSuchProfessionException(String message) {
        super(message);
    }
}