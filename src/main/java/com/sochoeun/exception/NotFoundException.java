package com.sochoeun.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException{
    public NotFoundException(String resourceName,Integer id) {
        super(HttpStatus.NOT_FOUND,String.format("%s with ID : %d Not Found",resourceName,id));
    }
}
