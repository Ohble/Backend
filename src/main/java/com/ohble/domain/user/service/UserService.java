package com.ohble.domain.user.service;

import com.ohble.domain.user.User;
import com.ohble.domain.user.repository.AdminRepository;
import com.ohble.global.exception.CustomException;
import com.ohble.global.exception.ExceptionType;
import com.ohble.global.jwt.JwtGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtGenerator jwtGenerator;

    public void login(String loginId, String password) {
        User user = adminRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ExceptionType.USER_NOT_VALIDATED));
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {

        }
    }
}