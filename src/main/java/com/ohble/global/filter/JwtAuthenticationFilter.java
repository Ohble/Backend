package com.ohble.global.filter;

import com.ohble.global.exception.CustomException;
import com.ohble.global.jwt.JwtResolver;
import com.ohble.global.jwt.JwtValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ohble.global.exception.ExceptionType.REQUIRE_TOKEN;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtValidator jwtValidator;
    private final JwtResolver jwtResolver;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        if (request.getHeader("Authorization") == null) {
            throw new CustomException(REQUIRE_TOKEN);
        }

        String token = request.getHeader("Authorization").substring(7);
        jwtValidator.validateToken(token);

//        String loginId = jwtResolver.jwtResolveToUserLoginId(token);
//        UserDetails requestUserDetails = userDetailsService.loadUserByUsername(loginId);
//
//        // JWT 를 바탕으로 인증 객체 생성
//        Authentication authToken = new UsernamePasswordAuthenticationToken(
//                requestUserDetails.getUsername(), requestUserDetails.getPassword());
//
//        // Authorities 부여
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);
//
//        // SecurityContextHolder 에 저장
//        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
