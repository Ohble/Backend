package com.ohble.global.security.filter;

import static com.ohble.global.exception.ExceptionType.PASSWORD_NOT_CORRECT;
import static com.ohble.global.exception.ExceptionType.USER_NOT_EXIST;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohble.domain.user.user.User;
import com.ohble.domain.user.user.controller.dto.UserRequestDto.LoginRequestForm;
import com.ohble.domain.user.user.repository.UserDetailsRepository;
import com.ohble.global.exception.CustomException;
import com.ohble.global.jwt.JwtGenerator;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationManager customAuthenticationManager;
    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtGenerator jwtGenerator;
    private final ObjectMapper mapper;
    public static final String HTTP_METHOD = "POST";
    public static final String REQUEST_URI = "/v1/user/login";
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER =
        new AntPathRequestMatcher("/v1/user/login", HTTP_METHOD);

    public LoginFilter(AuthenticationManager customAuthenticationManager,
        UserDetailsRepository userDetailsRepository, UserDetailsService userDetailsService,
        BCryptPasswordEncoder bCryptPasswordEncoder, JwtGenerator jwtGenerator,
        ObjectMapper objectMapper) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, null);
        this.customAuthenticationManager = customAuthenticationManager;
        this.userDetailsRepository = userDetailsRepository;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.mapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException, IOException {
        if (validateURI(request)) {
            LoginRequestForm loginRequestForm = extractLoginRequestForm(request);
            String requestLoginId = loginRequestForm.getLoginId();
            String requestPassword = loginRequestForm.getPassword();

            UserDetails userDetails = userDetailsService.loadUserByUsername(requestLoginId);

            // 비밀번호가 일치할 때
            if (bCryptPasswordEncoder.matches(requestPassword, userDetails.getPassword())) {
                if (userDetailsRepository.findByLoginId(userDetails.getUsername()).isEmpty()) {
                    setExceptionResponseForm(response, new CustomException(USER_NOT_EXIST));
                    response.flushBuffer();
                    return null;
                }
                User user = userDetailsRepository.findByLoginId(userDetails.getUsername()).get();

                Authentication authentication = createAuthenticationByLoginForm(user.getLoginId(),
                    user.getPassword());

                String accessToken = jwtGenerator.generateAccessToken(user);
                String refreshToken = jwtGenerator.generateRefreshToken(user);

                setSuccessResponseForm(response);
                registContextHolderForAuthentication(authentication);
                response.setHeader("Authorization",
                    jwtGenerator.wrapTokenPair(accessToken, refreshToken).toString());
                response.flushBuffer();
                return authentication;
            } else {
                setExceptionResponseForm(response, new CustomException(PASSWORD_NOT_CORRECT));
                response.flushBuffer();
                return null;
            }
        }
        throw new AuthenticationServiceException("Authorization method not supported");
    }

    private boolean validateURI(HttpServletRequest request) {
        if (request.getRequestURI().equals(REQUEST_URI) && request.getMethod().equals(HTTP_METHOD)
            && request.getContentType().equals("application/json")) {
            return true;
        }
        throw new AuthenticationServiceException("Requested method not supported");
    }

    private LoginRequestForm extractLoginRequestForm(HttpServletRequest request)
        throws IOException {
        return mapper.readValue(StreamUtils.copyToString(request.getInputStream(), UTF_8),
            LoginRequestForm.class);
    }

    private Authentication createAuthenticationByLoginForm(String loginId, String password) {
        return customAuthenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginId, password));
    }

    private void registContextHolderForAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void setExceptionResponseForm(HttpServletResponse response,
        CustomException customException) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(400);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("Exception", customException.getExceptionType());
        jsonResponse.put("Message", customException.getMessage());

        try {
            response.getWriter().print(jsonResponse);
        } catch (IOException e) {
            throw new InternalError(e);
        }
    }

    private void setSuccessResponseForm(HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(200);
    }
}