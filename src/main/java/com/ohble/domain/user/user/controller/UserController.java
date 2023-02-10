package com.ohble.domain.user.user.controller;

import com.ohble.domain.user.user.controller.dto.UserRequestDto.AuthRequestForm;
import com.ohble.domain.user.user.controller.dto.UserRequestDto.JoinRequestForm;
import com.ohble.domain.user.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "관리자 유저 생성", notes = "로그인 아이디와 비밀번호를 담아 요청한다.")
    @ResponseStatus(OK)
    @PostMapping
    public Map<String, Long> join(@RequestBody JoinRequestForm joinRequestForm) {
        System.out.println("joinRequestForm = " + joinRequestForm.getLoginId());
        System.out.println("joinRequestForm = " + joinRequestForm.getPassword());
        return userService.join(joinRequestForm);
    }

    @ApiOperation(value = "관리자 유저 인증", notes = "인증을 수행할 유저의 Index 값과 인증번호를 담아 요청한다.")
    @ResponseStatus(OK)
    @PostMapping("/auth")
    public Map<String, Boolean> auth(@RequestBody AuthRequestForm authRequestForm) {
        return userService.auth(authRequestForm);
    }
}
