package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ResultCode {
    NAME_DUPLICATION(HttpStatus.INTERNAL_SERVER_ERROR,"NAME-DUPLICATED-ERR-500","동일한 이름이 존재합니다."),
    ID_NOT_EXIST(HttpStatus.INTERNAL_SERVER_ERROR,"ID-NOT-EXIST-ERR-500","해당 ID를 가진 데이터가 없습니다."),
    NON_AUTHORITATIVE_INFORMATION(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "NON-AUTHORITATIVE-ERR-400", "수정 / 삭제 권한이 없습니다."),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "NOT-ACCEPTABLE-QUERY-ERR-400", "수행할 수 없습니다.(무결성 제약 조건 위반)");
    private HttpStatus status;
    private String code;
    private String message;
}
