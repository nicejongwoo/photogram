package com.example.photogram.handler;

import com.example.photogram.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
//    public CMRespDto<?> validationException(CustomValidationException e) {
    public String validationException(CustomValidationException e) {
//        return new CMRespDto(e.getMessage(), e.getErrorMap(), -1);
        return Script.back(e.getErrorMap().toString());
    }
}
