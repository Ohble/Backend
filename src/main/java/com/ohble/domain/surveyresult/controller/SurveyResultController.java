package com.ohble.domain.surveyresult.controller;

import com.ohble.domain.surveyresult.SurveyResult;
import com.ohble.domain.surveyresult.controller.dto.SurveyResultRequestDto.SubmitSurveyRequestForm;
import com.ohble.domain.surveyresult.service.SurveyResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/survey-result")
public class SurveyResultController {

    private final SurveyResultService surveyResultService;

    @ResponseStatus(OK)
    @PostMapping
    public Map<String, Boolean> submitSurveyResult(@RequestBody SubmitSurveyRequestForm submitSurveyRequestForm) {
        return surveyResultService.executeSubmitSurveyResult(
                submitSurveyRequestForm.getParticipantId(),
                submitSurveyRequestForm.questionToList(), submitSurveyRequestForm.answerToList());
    }

    @ResponseStatus(OK)
    @GetMapping
    public List<SurveyResult> loadAllSurveyResult() {
        return surveyResultService.executeLoadAllSurveyResult();
    }
}
