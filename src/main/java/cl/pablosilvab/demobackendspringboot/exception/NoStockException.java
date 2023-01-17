package cl.pablosilvab.demobackendspringboot.exception;

public class NoStockException extends Exception {

    public NoStockException() {
        super();
    }

    public NoStockException(String msg) {
        super(msg);
    }

    public NoStockException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
