package com.example.demo.exception;

import lombok.Getter;

@Getter
public class IdNotExistException extends Exception{
    private ResultCode resultCode;

    public IdNotExistException(String message, ResultCode resultCode){
        super(message);
        this.resultCode = resultCode;
    }
}
