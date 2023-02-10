package com.ohble.domain.survey.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class SurveyResponseDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class GetAllSurveyInformationForm {
        private Long id;
        private String questionContent;
        private String answerContent1;
        private String answerContent2;
        private String answerContent3;
        private String answerContent4;
        private String answerContent5;
        private String answerContent6;
        private String answerContent7;
        private String answerContent8;
        private String answerContent9;
        private String answerContent10;
    }
}
