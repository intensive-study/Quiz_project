package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ResultCode {
    NAME_DUPLICATION(HttpStatus.NOT_ACCEPTABLE,"NAME-DUPLICATED-ERR-406","동일한 이름이 존재합니다."),
    ID_NOT_EXIST(HttpStatus.NOT_FOUND,"ID-NOT-EXIST-ERR-404","해당 ID를 가진 데이터가 없습니다."),
    USERNAME_NOT_EXIST(HttpStatus.INTERNAL_SERVER_ERROR,"USERNAME-NOT-EXIST-ERR-500","해당 USERNAME 가진 데이터가 없습니다."),
    NON_AUTHORITATIVE_INFORMATION(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "NON-AUTHORITATIVE-ERR-203", "수정 / 삭제 권한이 없습니다."),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "NOT-ACCEPTABLE-QUERY-ERR-406", "해당 명령을 수행할 수 없습니다. (데이터 중복 / 무결성 제약 위배)"),
    BAD_REQUEST_ARGUMENT(HttpStatus.BAD_REQUEST, "BAD_REQUEST_ARGUMENT-400", "메소드 호출 전달 값이 올바르지 않습니다.");
    private HttpStatus status;
    private String code;
    private String message;
}
