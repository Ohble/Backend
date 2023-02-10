package com.ohble.domain.user.useremailauth.service;

import com.ohble.domain.user.user.User;
import com.ohble.domain.user.useremailauth.UserEmailAuth;
import com.ohble.domain.user.useremailauth.repository.UserEmailAuthRepository;
import com.ohble.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Date;

import static com.ohble.global.exception.ExceptionType.USER_NOT_EXIST;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserEmailAuthService {

    private final UserEmailAuthRepository userEmailAuthRepository;

    public UserEmailAuth loadUserEmailAuthByUser(User user) {
        if (userEmailAuthRepository.findByUser(user).isPresent()) {
            return userEmailAuthRepository.findByUser(user).get();
        }
        throw new CustomException(USER_NOT_EXIST);
    }

    public String saveUserEmailAuth(User user) {
        UserEmailAuth userEmailAuth = UserEmailAuth.builder()
                .payload(createEmailAuthPayload())
                .user(user)
                .build();
        userEmailAuthRepository.save(userEmailAuth);
        return userEmailAuth.getPayload();
    }

    private String createEmailAuthPayload() {
        StringBuilder payload = new StringBuilder();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        char[] charNumberSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        int idx = 0;
        int numberLen = charNumberSet.length;

        // 인증번호는 8자리로 이루어져있다.
        for (int i = 0; i < 8; i++) {
            idx = sr.nextInt(numberLen);
            payload.append(charNumberSet[idx]);
        }
        return payload.toString();
    }
}
