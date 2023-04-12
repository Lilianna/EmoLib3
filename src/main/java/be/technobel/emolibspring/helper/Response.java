package be.technobel.emolibspring.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Response {
    public static ResponseEntity setResponse(boolean success, HttpStatus status, Object data) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("success", success);
        response.put("status", status.value());
        response.put("data", data);
        return ResponseEntity.status(status).body(response);
    }
}
