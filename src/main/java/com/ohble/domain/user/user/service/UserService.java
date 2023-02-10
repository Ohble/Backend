package com.ohble.domain.user.user.service;

import com.ohble.domain.user.user.User;
import com.ohble.domain.user.user.controller.dto.UserRequestDto.AuthRequestForm;
import com.ohble.domain.user.user.controller.dto.UserRequestDto.JoinRequestForm;
import com.ohble.domain.user.user.repository.UserRepository;
import com.ohble.domain.user.useremailauth.UserEmailAuth;
import com.ohble.domain.user.useremailauth.service.UserEmailAuthService;
import com.ohble.global.exception.CustomException;
import com.ohble.global.ses.SendEmailServiceBySES;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.ohble.global.exception.ExceptionType.USER_NOT_EXIST;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SendEmailServiceBySES sendEmailServiceBySES;
    private final UserEmailAuthService userEmailAuthService;

    @Transactional
    public Map<String, Long> join(JoinRequestForm joinRequestForm) {
        User user = User.builder()
                .loginId(joinRequestForm.getLoginId())
                .password(bCryptPasswordEncoder.encode(joinRequestForm.getPassword()))
                .status("NOT_AUTH")
                .build();
        userRepository.save(user);

        String payload = userEmailAuthService.saveUserEmailAuth(user);
        sendEmailServiceBySES.sendAdminAuthContent("kitten.diger@gmail.com", payload);

        return new HashMap<>() {{
            put("userId", user.getId());
        }};
    }

    @Transactional
    public Map<String, Boolean> auth(AuthRequestForm authRequestForm) {
        Optional<User> user = userRepository.findById(authRequestForm.getUserId());
        if (user.isPresent()) {
            UserEmailAuth userEmailAuth = userEmailAuthService.loadUserEmailAuthByUser(user.get());
            if (userEmailAuth.getPayload().equals(authRequestForm.getAuthPayload())) {
                user.get().updateStatus();
                return new HashMap<>() {{
                    put("Success", true);
                }};
            }
        }
        throw new CustomException(USER_NOT_EXIST);
    }
}