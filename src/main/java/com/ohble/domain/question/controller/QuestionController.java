package com.ohble.domain.question.controller;

import com.ohble.domain.question.dto.QuestionRequestDto.CreateForm;
import com.ohble.domain.question.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @ApiOperation(value = "질문 생성", notes = "질문을 생성한다.")
    @PostMapping
    public ResponseEntity<Object> createQuestion(
            @RequestBody @Valid CreateForm createForm) {
        questionService.createQuestion(createForm.getContent());
        return ResponseEntity
                .ok()
                .body(new HashMap<>() {{
                    put("Success", true);
                }});
    }

    @GetMapping
    public ResponseEntity<Object> getQuestions() {
        return ResponseEntity
                .ok()
                .body(questionService.loadAllQuestion());
    }
}
