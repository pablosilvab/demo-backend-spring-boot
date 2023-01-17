package cl.pablosilvab.demobackendspringboot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;
    private Object errors;

    public void addErrorMsgToResponse(String msgError) {
        ResponseError error = new ResponseError().setDetails(msgError);
        setErrors(error);
    }
}
