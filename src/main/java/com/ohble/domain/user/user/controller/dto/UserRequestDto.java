package com.ohble.domain.user.user.controller.dto;

import lombok.Getter;

public class UserRequestDto {

    @Getter
    public static class LoginRequestForm {
        private String loginId;
        private String password;
    }

    @Getter
    public static class JoinRequestForm {
        private String loginId;
        private String password;
    }

    @Getter
    public static class AuthRequestForm {
        private Long userId;
        private String authPayload;
    }
}
