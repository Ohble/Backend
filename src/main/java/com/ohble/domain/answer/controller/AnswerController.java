package com.ohble.domain.answer.controller;

import com.ohble.domain.answer.dto.AnswerRequestDto.CreateForm;
import com.ohble.domain.answer.service.AnswerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/answers")
public class AnswerController {

    private final AnswerService answerService;

    @ApiOperation(value = "답변 생성", notes = "질문에 대한 답변을 생성한다.")
    @PostMapping
    public ResponseEntity<Object> createAnswer(
            @RequestBody @Valid CreateForm createForm) {
        answerService.createAnswer(createForm.getContent(), createForm.getQuestionId());
        return ResponseEntity
                .ok()
                .body(new HashMap<>() {{
                    put("Success", true);
                }});
    }
}