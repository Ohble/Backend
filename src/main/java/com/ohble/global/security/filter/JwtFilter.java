package com.ohble.global.security.filter;

import com.ohble.global.exception.CustomException;
import com.ohble.global.jwt.JwtResolver;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import java.util.List;

import static com.ohble.global.exception.ExceptionType.REQUIRE_TOKEN;
import static com.ohble.global.exception.ExceptionType.USER_UNAUTHORIZED;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final List<String> whiteListURI;
    private final JwtResolver jwtResolver;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // WhiteList에 포함되어있지 않으면 조건문 들어감
        if (!isRequestURIWhiteList(request)) {
            if (isNotContainedToken(request, response)) {
                setExceptionResponseForm(response, new CustomException(REQUIRE_TOKEN));
                response.flushBuffer();
                return;
            }
            String token = request.getHeader("Authorization").substring(7);
            if (jwtResolver.jwtResolveToUserStatus(token).equals("NOT_AUTH")) {
                setExceptionResponseForm(response, new CustomException(USER_UNAUTHORIZED));
                response.flushBuffer();
                return;
            }
            registContextHolderForAuthentication(jwtResolver.jwtResolveToUserLoginId(token));
        }
        filterChain.doFilter(request, response);
    }

    private Authentication getAuthentication(String loginId) {
        UserDetails user = userDetailsService.loadUserByUsername(loginId);
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    private void registContextHolderForAuthentication(String loginId) {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(loginId));
    }

    // WhiteList에 포함되었으면 True,
    private boolean isRequestURIWhiteList(HttpServletRequest request) {
        return whiteListURI.contains(request.getRequestURI());
    }

    private boolean isNotContainedToken(HttpServletRequest request, HttpServletResponse response) {
        return request.getHeader("Authorization") == null ||
                !request.getHeader("Authorization").startsWith("Bearer ");
    }

    private void setExceptionResponseForm(HttpServletResponse response, CustomException customException) {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("Exception", customException.getExceptionType());

        try {
            response.getWriter().print(jsonResponse);
        } catch (IOException e) {
            throw new InternalError(e);
        }
    }
}
