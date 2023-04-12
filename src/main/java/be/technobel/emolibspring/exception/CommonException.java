package be.technobel.emolibspring.exception;

import be.technobel.emolibspring.helper.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CommonException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", ex.getMessage());
        return Response.setResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, response);
    }
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<String> handleBadRequestException(HttpClientErrorException.BadRequest exception) {
        // build a custom error response
        String errorResponse = "{ \"error\": \"Bad Request\", \"message\": \"" + exception.getMessage() + "\" }";
        // return the error response
        return ResponseEntity.badRequest().body(errorResponse);
    }
}