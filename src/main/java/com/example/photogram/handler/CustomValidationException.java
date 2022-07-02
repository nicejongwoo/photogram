package com.example.photogram.handler;


import java.util.Map;

public class CustomValidationException extends RuntimeException {

    private Map<String, Object> errorMap;

    public CustomValidationException(String message, Map<String, Object> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, Object> getErrorMap() {
        return errorMap;
    }
}
