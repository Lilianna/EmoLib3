package be.technobel.emolibspring.exception;

import be.technobel.emolibspring.helper.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationHandleException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handleExceptionBadRequest(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<String, String>();
        exception.getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return Response.setResponse(false, HttpStatus.BAD_REQUEST, errorMap);
    }
}
