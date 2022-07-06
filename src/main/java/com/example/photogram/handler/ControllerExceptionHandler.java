package com.example.photogram.handler;

import com.example.photogram.handler.exception.CustomApiException;
import com.example.photogram.handler.exception.CustomException;
import com.example.photogram.util.Script;
import com.example.photogram.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value = {CustomValidationException.class})
    public String validationException(CustomValidationException e) {
        if (e.getErrorMap() == null) {
            return Script.back(e.getMessage());
        } else {
            return Script.back(e.getErrorMap().toString());
        }
    }

    @ExceptionHandler(value = {CustomException.class})
    public String exception(CustomException e) {
        return Script.back(e.getMessage());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CMRespDto<>(e.getMessage(), e.getErrorMap(), -1));
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CMRespDto<>(e.getMessage(), null, -1));
    }
}
