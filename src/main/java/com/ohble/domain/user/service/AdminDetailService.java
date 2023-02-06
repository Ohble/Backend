package com.ohble.domain.user.service;

import com.ohble.domain.user.repository.UserRepository;
import com.ohble.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.ohble.global.exception.ExceptionType.USER_NOT_EXIST;

@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLoginId(username)
                .orElseThrow(() -> new CustomException(USER_NOT_EXIST));
    }
}
