package com.ohble.domain.refreshtoken.controller;

import com.ohble.domain.refreshtoken.RefreshToken;
import com.ohble.domain.refreshtoken.repository.RefreshTokenRepository;
import com.ohble.domain.user.user.User;
import com.ohble.global.exception.CustomException;
import com.ohble.global.jwt.JwtGenerator;
import com.ohble.global.jwt.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.ohble.global.exception.ExceptionType.JWT_MALFORMED_EXCEPTION;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RequestMapping("/token")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TokenController {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtGenerator jwtGenerator;
    private final JwtValidator jwtValidator;

    // 토큰 갱신 (RefreshToken 을 매개변수로 받는다.)
    @PostMapping
    public ResponseEntity<Object> updateToken(@RequestHeader String authorization) {

        String requestRefreshToken = authorization.substring(7);

        // RefreshToken 검증이 끝나면, 토큰 재발급
        if (jwtValidator.validateToken(requestRefreshToken) &&
                refreshTokenRepository.findByPayload(requestRefreshToken).isPresent()) {

            RefreshToken requestRefreshTokenDomain = refreshTokenRepository.findByPayload(requestRefreshToken).get();

            User requestUser = requestRefreshTokenDomain.getUser();

            String accessToken = jwtGenerator.generateAccessToken(requestUser);
            String refreshToken = jwtGenerator.updateRefreshToken(requestUser);
            Map<String, String> result = jwtGenerator.wrapTokenPair(accessToken, refreshToken);

            HttpHeaders response = new HttpHeaders();
            response.set("Authorization", result.toString());

            return ResponseEntity.status(OK)
                    .headers(response)
                    .body(new HashMap<>() {{
                        put("Success", true);
                    }});
        }
        throw new CustomException(JWT_MALFORMED_EXCEPTION);
    }
}
