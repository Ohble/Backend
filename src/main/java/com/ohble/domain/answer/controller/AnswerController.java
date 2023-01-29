package com.ohble.domain.answer.controller;

import com.ohble.domain.answer.dto.AnswerRequestDto.CreateForm;
import com.ohble.domain.answer.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/answer")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<Object> createAnswer(
            @RequestBody @Valid CreateForm createForm) {
        answerService.createAnswer(createForm.getContent());
        return ResponseEntity
                .ok()
                .body(new HashMap<>() {{
                    put("Success", true);
                }});
    }
}
