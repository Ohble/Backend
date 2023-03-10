package com.ohble.global.security.authentication.details;

import com.ohble.domain.user.user.User;
import com.ohble.domain.user.user.repository.UserDetailsRepository;
import com.ohble.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ohble.global.exception.ExceptionType.USER_NOT_EXIST;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailsRepository userRepository;

    @Override
    public User loadUserByUsername(String loginId) throws UsernameNotFoundException {
        if (userRepository.findByLoginId(loginId).isPresent()) {
            return userRepository.findByLoginId(loginId).get();
        }
        throw new CustomException(USER_NOT_EXIST);
    }
}