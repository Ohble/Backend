package com.ohble.domain.surveyresult.controller;

import com.ohble.domain.surveyresult.SurveyResult;
import com.ohble.domain.surveyresult.controller.dto.SurveyResultRequestDto.SubmitSurveyRequestForm;
import com.ohble.domain.surveyresult.service.SurveyResultService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/survey-result")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SurveyResultController {

    private final SurveyResultService surveyResultService;

    @ApiOperation(value = "설문 조사 결과를 제출한다.", notes = "질문과 그에대한 응답을 알맞게 보내주어 등록한다.")
    @ResponseStatus(OK)
    @PostMapping
    public Map<String, Boolean> submitSurveyResult(@RequestBody SubmitSurveyRequestForm submitSurveyRequestForm) {
        return surveyResultService.executeSubmitSurveyResult(
                submitSurveyRequestForm.getParticipantId(),
                submitSurveyRequestForm.questionToList(), submitSurveyRequestForm.answerToList());
    }

    @ApiOperation(value = "지금까지 제출된 설문조사 내역을 보여준다.", notes = "설문 조사를 제출할 때와 폼은 같다.")
    @ResponseStatus(OK)
    @GetMapping
    public List<SurveyResult> loadAllSurveyResult() {
        return surveyResultService.executeLoadAllSurveyResult();
    }
}
