package com.example.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class NameDuplicateException extends Exception{

    private ResultCode resultCode;

    public NameDuplicateException(String message, ResultCode resultCode){
        super(message);
        this.resultCode = resultCode;
    }
}
