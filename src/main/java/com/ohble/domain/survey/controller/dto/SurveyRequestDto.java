package com.ohble.domain.survey.controller.dto;

import lombok.Getter;

public class SurveyRequestDto {

    @Getter
    public static class AddSurveyForm {
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

    @Getter
    public static class EditSurveyForm {
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
