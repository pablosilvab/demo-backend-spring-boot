package cl.pablosilvab.demobackendspringboot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError {

    private String field;
    private String message;
    private Object rejectedValue;

    public ValidationError(String field, String message, Object rejectedValue) {
        this.field = field;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }
}
