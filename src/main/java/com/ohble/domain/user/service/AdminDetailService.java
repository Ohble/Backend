package com.ohble.domain.user.service;

import com.ohble.domain.user.repository.AdminRepository;
import com.ohble.global.exception.CustomException;
import com.ohble.global.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class AdminDetailService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByLoginId(username)
                .orElseThrow(() -> new CustomException(ExceptionType.USER_NOT_VALIDATED));
    }
}
