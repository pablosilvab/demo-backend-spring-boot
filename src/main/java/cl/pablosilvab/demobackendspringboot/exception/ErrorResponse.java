package cl.pablosilvab.demobackendspringboot.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ErrorResponse {

    private String error;
    private String message;
    private int status;
    private List<ValidationError> errors;

    public ErrorResponse(String error, String message, int status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public ErrorResponse(String error, String message, HttpStatus status) {
        this.error = error;
        this.message = message;
        this.status = status.value();
    }

    public void setValidationErrors(List<ValidationError> errors) {
        this.errors = errors;
    }
}

