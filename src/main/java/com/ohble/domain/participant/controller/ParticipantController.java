package com.ohble.domain.participant.controller;

import com.ohble.domain.participant.service.ParticipantService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/participant")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParticipantController {

    private final ParticipantService participantService;

    @ApiOperation(value = "설문조사를 참여하는 사용자의 인덱스를 발급 받는다.")
    @PostMapping
    public Map<String, Long> generateParticipant() {
        return participantService.generateParticipant();
    }
}
