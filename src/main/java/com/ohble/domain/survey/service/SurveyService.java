package com.ohble.domain.survey.service;

import com.ohble.domain.survey.Survey;
import com.ohble.domain.survey.controller.dto.SurveyRequestDto.AddSurveyForm;
import com.ohble.domain.survey.controller.dto.SurveyRequestDto.EditSurveyForm;
import com.ohble.domain.survey.controller.dto.SurveyResponseDto.GetAllSurveyInformationForm;
import com.ohble.domain.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public List<GetAllSurveyInformationForm> loadAllSurvey() {
        List<Survey> all = surveyRepository.findAll();
        List<GetAllSurveyInformationForm> response = new ArrayList<>();

        for (Survey survey : all) {
            response.add(GetAllSurveyInformationForm.builder()
                    .id(survey.getId())
                    .questionContent(survey.getQuestionContent())
                    .answerContent1(survey.getAnswerContent1())
                    .answerContent2(survey.getAnswerContent2())
                    .answerContent3(survey.getAnswerContent3())
                    .answerContent4(survey.getAnswerContent4())
                    .answerContent5(survey.getAnswerContent5())
                    .answerContent6(survey.getAnswerContent6())
                    .answerContent7(survey.getAnswerContent7())
                    .answerContent8(survey.getAnswerContent8())
                    .answerContent9(survey.getAnswerContent9())
                    .answerContent10(survey.getAnswerContent10())
                    .build());
        }
        return response;
    }

    @Transactional
    public Map<String, Boolean> addSurveyForm(AddSurveyForm addSurveyForm) {
        Survey survey = Survey.builder()
                .questionContent(addSurveyForm.getQuestionContent())
                .answerContent1(addSurveyForm.getAnswerContent1())
                .answerContent2(addSurveyForm.getAnswerContent2())
                .answerContent3(addSurveyForm.getAnswerContent3())
                .answerContent4(addSurveyForm.getAnswerContent4())
                .answerContent5(addSurveyForm.getAnswerContent5())
                .answerContent6(addSurveyForm.getAnswerContent6())
                .answerContent7(addSurveyForm.getAnswerContent7())
                .answerContent8(addSurveyForm.getAnswerContent8())
                .answerContent9(addSurveyForm.getAnswerContent9())
                .answerContent10(addSurveyForm.getAnswerContent10())
                .build();

        surveyRepository.save(survey);

        return new HashMap<>() {{
            put("Success", true);
        }};
    }

    @Transactional
    public Map<String, Boolean> editSurveyForm(Long surveyId, EditSurveyForm editSurveyForm) {
        Survey survey = Survey.builder()
                .id(surveyId)
                .questionContent(editSurveyForm.getQuestionContent())
                .answerContent1(editSurveyForm.getAnswerContent1())
                .answerContent2(editSurveyForm.getAnswerContent2())
                .answerContent3(editSurveyForm.getAnswerContent3())
                .answerContent4(editSurveyForm.getAnswerContent4())
                .answerContent5(editSurveyForm.getAnswerContent5())
                .answerContent6(editSurveyForm.getAnswerContent6())
                .answerContent7(editSurveyForm.getAnswerContent7())
                .answerContent8(editSurveyForm.getAnswerContent8())
                .answerContent9(editSurveyForm.getAnswerContent9())
                .answerContent10(editSurveyForm.getAnswerContent10())
                .build();
        surveyRepository.save(survey);

        return new HashMap<>() {{
            put("Success", true);
        }};
    }

    @Transactional
    public Map<String, Boolean> deleteSurveyForm(Long surveyId) {
        surveyRepository.deleteById(surveyId);
        return new HashMap<>() {{
            put("Success", true);
        }};
    }
}
