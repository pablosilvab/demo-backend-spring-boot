package cl.pablosilvab.demobackendspringboot.exception;

public class InvalidProductTypeException extends RuntimeException {
    public InvalidProductTypeException(String message) {
        super(message);
    }
}