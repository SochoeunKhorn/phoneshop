package com.sochoeun.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> apiHandler(ApiException exception){
        ErrorResponse response = new ErrorResponse(exception.getStatus(),exception.getMessage());
        return ResponseEntity.status(exception.getStatus()).body(response);
    }
}
