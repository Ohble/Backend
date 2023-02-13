package com.ohble.domain.participant.controller;

import com.ohble.domain.participant.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/participant")
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping
    public Map<String, Long> generateParticipant() {
        return participantService.generateParticipant();
    }
}
