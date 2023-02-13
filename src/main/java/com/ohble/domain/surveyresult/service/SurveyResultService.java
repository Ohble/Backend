package com.ohble.domain.surveyresult.service;

import com.ohble.domain.participant.service.ParticipantService;
import com.ohble.domain.surveyresult.SurveyResult;
import com.ohble.domain.surveyresult.repository.SurveyResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SurveyResultService {

    private final SurveyResultRepository surveyResultRepository;
    private final ParticipantService participantService;


    public Map<String, Boolean> executeSubmitSurveyResult(Long participantId, List<String> questions, List<String> answers) {
        SurveyResult surveyResult = new SurveyResult();
        surveyResult.setSurveyResult(participantService.loadParticipantById(participantId), questions, answers);
        surveyResultRepository.save(surveyResult);

        return new HashMap<>() {{
            put("Success", true);
        }};
    }

    public List<SurveyResult> executeLoadAllSurveyResult() {
        return surveyResultRepository.findAll();
    }
}
