package com.ohble.global.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ExceptionType {
    REQUIRE_TOKEN(BAD_REQUEST, "인가되지 않은 사용자입니다."),
    USER_NOT_VALIDATED(BAD_REQUEST, "존재하지 않는 아이디거나, 비밀번호가 일치하지 않습니다."),
    JWT_MALFORMED_EXCEPTION(BAD_REQUEST, "토큰이 손상되었습니다. 다시 로그인 해주세요")

    ;

    private final HttpStatus status;
    private final String message;
}
