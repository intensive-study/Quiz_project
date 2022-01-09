package com.example.demo.exception;

import lombok.Getter;

@Getter
public class NameDuplicateException extends Exception{

    private ResultCode resultCode;

    public NameDuplicateException(String message, ResultCode resultCode){
        super(message);
        this.resultCode = resultCode;
    }
}
