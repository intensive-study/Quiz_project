package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ResultCode {
    NAME_DUPLICATION(HttpStatus.INTERNAL_SERVER_ERROR,"NAME-DUPLICATED-ERR-404","동일한 이름이 존재합니다."),;

    private HttpStatus status;
    private String code;
    private String message;
}
