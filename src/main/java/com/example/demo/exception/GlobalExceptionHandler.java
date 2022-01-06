package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NameDuplicateException.class)
    public ResponseEntity<ExceptionResponse> handleNameDuplicateException(NameDuplicateException e){
        log.error("handleNameDuplicateException",e);
        ExceptionResponse response = new ExceptionResponse(e.getResultCode());
        return new ResponseEntity<>(response, e.getResultCode().getStatus());
    }

    @ExceptionHandler(IdNotExistException.class)
    public ResponseEntity<ExceptionResponse> handleIdNotExistException(IdNotExistException e){
        log.error("handleIdNotExistException",e);
        ExceptionResponse response = new ExceptionResponse(e.getResultCode());
        return new ResponseEntity<>(response, e.getResultCode().getStatus());
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException ex) {
        log.error("handleInvalidDataAccessApiUsageException", ex);
        ExceptionResponse response = new ExceptionResponse(ResultCode.NON_AUTHORITATIVE_INFORMATION);
        return new ResponseEntity<>(response, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("handleDataIntegrityViolationException", ex);
        ExceptionResponse response = new ExceptionResponse(ResultCode.NOT_ACCEPTABLE);
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

}
