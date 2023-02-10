package com.ohble.domain.survey.controller;

import com.ohble.domain.survey.controller.dto.SurveyRequestDto.AddSurveyForm;
import com.ohble.domain.survey.controller.dto.SurveyRequestDto.EditSurveyForm;
import com.ohble.domain.survey.controller.dto.SurveyResponseDto.GetAllSurveyInformationForm;
import com.ohble.domain.survey.service.SurveyService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/survey")
public class SurveyController {

    private final SurveyService surveyService;

    @ApiOperation(value = "관리자가 게시한 설문조사 양식을 모두 불러온다.")
    @ResponseStatus(OK)
    @GetMapping
    public List<GetAllSurveyInformationForm> getAllSurveyInformationForm() {
        return surveyService.loadAllSurvey();
    }

    @ApiOperation(value = "설문조사 양식을 작성하여 요청한다.")
    @ResponseStatus(OK)
    @PostMapping
    public Map<String, Boolean> saveSurveyInformation(@RequestBody AddSurveyForm addSurveyForm) {
        return surveyService.addSurveyForm(addSurveyForm);
    }

    @ApiOperation(value = "설문조사 양식을 수정한다.", notes = "어떤 설문조사 양식을 수정할지 PathVariable로 요청한다.")
    @ResponseStatus(OK)
    @PutMapping("{surveyId}")
    public Map<String, Boolean> saveSurveyInformation(
            @PathVariable Long surveyId,
            @RequestBody EditSurveyForm editSurveyForm) {
        return surveyService.editSurveyForm(surveyId, editSurveyForm);
    }

    @ApiOperation(value = "설문조사 양식을 삭제한다.", notes = "어떤 설문조사 양식을 삭제할지 PathVariable로 요청한다.")
    @ResponseStatus(OK)
    @DeleteMapping("{surveyId}")
    public Map<String, Boolean> saveSurveyInformation(@PathVariable Long surveyId) {
        return surveyService.deleteSurveyForm(surveyId);
    }
}
