package com.ohble.global.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohble.domain.user.user.repository.UserDetailsRepository;
import com.ohble.global.jwt.JwtGenerator;
import com.ohble.global.jwt.JwtResolver;
import com.ohble.global.security.filter.JwtFilter;
import com.ohble.global.security.filter.LoginFilter;
import com.ohble.global.util.factory.BCryptPasswordFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationManager customAuthenticationManager;
    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsService userDetailsService;
    private final JwtGenerator jwtGenerator;
    private final JwtResolver jwtResolver;
    private final ObjectMapper mapper;
    private final List<String> whiteListURI = List.of(
        "/v1/user", "/v1/user/auth", "/swagger-resources/**", "/v1/survey-result",
        "/v1/participant",
        "/swagger-ui.html",
        "/v2/api-docs",
        "/webjars/**",
        "/webjars/springfox-swagger-ui/springfox.css",
        "/webjars/springfox-swagger-ui/swagger-ui.css",
        "/webjars/springfox-swagger-ui/swagger-ui-bundle.js",
        "/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js",
        "/webjars/springfox-swagger-ui/springfox.js",
        "/webjars/springfox-swagger-ui/favicon-32x32.png",
        "/swagger-resources/configuration/ui",
        "/swagger-resources/configuration/security",
        "/swagger-resources"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .httpBasic().disable()
            .formLogin().disable()
            .logout().disable()
            .headers().frameOptions().disable()
            .and()
            .sessionManagement().sessionCreationPolicy(STATELESS);
        http
            .authorizeRequests()
            .antMatchers(String.valueOf(whiteListURI))
            .permitAll()
        ;
        http
            //.addFilterBefore(corsFilter(), ChannelProcessingFilter.class)
            .addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    public LoginFilter loginFilter() {
        return new LoginFilter(
            customAuthenticationManager,
            userDetailsRepository,
            userDetailsService,
            bCryptPasswordEncoder(),
            jwtGenerator, mapper);
    }

    public JwtFilter jwtFilter() {
        return new JwtFilter(whiteListURI, jwtResolver, userDetailsService);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return BCryptPasswordFactory.getBCryptPasswordEncoder();
    }
}