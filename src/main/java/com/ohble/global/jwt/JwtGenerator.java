package com.ohble.global.jwt;

import com.ohble.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional

public class JwtGenerator {
    @Value("${spring.jwt.secret-key}")
    private String secretKey;
    // 테스트 환경 JWT 만료기간 2
    private final long ACCESS_TOKEN_EXPIRE_TIME = 14 * 24 * 60 * 60 * 1000L; // 14일

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // AccessToken 생성
    public String generateAccessToken(User user) {
        Date accessTokenExpireIn = new Date(new Date().getTime() + ACCESS_TOKEN_EXPIRE_TIME);

        Claims claims = Jwts.claims();
        claims.setSubject("JWT");
        claims.put("loginId", user.getLoginId());

        // Bearer Access Token 생성
        return "Bearer " + Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .setExpiration(accessTokenExpireIn)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    // AccessToken 생성
    public String generateAccessToken(Authentication authentication) {

        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Date accessTokenExpireIn = new Date(new Date().getTime() + ACCESS_TOKEN_EXPIRE_TIME);

        Claims claims = (Claims) Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpireIn)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512);

        // Bearer Access Token 생성
        return "Bearer " + Jwts.builder()
                .setSubject(authentication.getName())
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .setExpiration(accessTokenExpireIn)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }
}
