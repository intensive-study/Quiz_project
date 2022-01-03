package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 카테고리 명이 중복되는 경우 발생
     */
    @ExceptionHandler(NameDuplicateException.class)
    public ResponseEntity<ExceptionResponse> handleNameDuplicateException(NameDuplicateException e){
        log.error("handleNameDuplicateException",e);
        ExceptionResponse response = new ExceptionResponse(e.getResultCode());
        return new ResponseEntity<>(response, e.getResultCode().getStatus());
    }

}
