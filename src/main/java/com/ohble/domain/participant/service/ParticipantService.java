package com.ohble.domain.participant.service;

import com.ohble.domain.participant.Participant;
import com.ohble.domain.participant.repository.ParticipantRepository;
import com.ohble.global.exception.CustomException;
import com.ohble.global.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public Map<String, Long> generateParticipant() {
        Participant participant = new Participant();
        participantRepository.save(participant);
        return new HashMap<>() {{
            put("participantId", participant.getId());
        }};
    }

    public Participant loadParticipantById(Long participantId) {
        Optional<Participant> byId = participantRepository.findById(participantId);

        if (byId.isPresent()) {
            return byId.get();
        }
        throw new CustomException(ExceptionType.USER_NOT_EXIST);
    }
}
