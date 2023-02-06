package com.ohble.domain.user.controller.dto;

import lombok.Getter;

public class UserRequestDto {

    @Getter
    public static class LoginRequestForm {
        private String loginId;
        private String password;
    }
}
