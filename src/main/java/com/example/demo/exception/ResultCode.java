package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ResultCode {
    NAME_DUPLICATION(HttpStatus.INTERNAL_SERVER_ERROR,"NAME-DUPLICATED-ERR-400","동일한 이름이 존재합니다."),
    ID_NOT_EXIST(HttpStatus.INTERNAL_SERVER_ERROR,"ID_NOT_EXIST-ERR-400","해당 ID가 존재하지 않습니다."),;

    private HttpStatus status;
    private String code;
    private String message;
}
