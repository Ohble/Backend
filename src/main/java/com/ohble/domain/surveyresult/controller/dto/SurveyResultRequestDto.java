package com.ohble.domain.surveyresult.controller.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SurveyResultRequestDto {

    @Getter
    public static class SubmitSurveyRequestForm {
        private Long participantId;

        private String question1;
        private String answer1;

        private String question2;
        private String answer2;

        private String question3;
        private String answer3;

        private String question4;
        private String answer4;

        private String question5;
        private String answer5;

        private String question6;
        private String answer6;

        private String question7;
        private String answer7;

        private String question8;
        private String answer8;

        private String question9;
        private String answer9;

        private String question10;
        private String answer10;

        private String question11;
        private String answer11;

        private String question12;
        private String answer12;

        private String question13;
        private String answer13;

        private String question14;
        private String answer14;

        public List<String> questionToList() {
            List<String> questions = new ArrayList<>();
            questions.add(question1);
            questions.add(question2);
            questions.add(question3);
            questions.add(question4);
            questions.add(question5);
            questions.add(question6);
            questions.add(question7);
            questions.add(question8);
            questions.add(question9);
            questions.add(question10);
            questions.add(question11);
            questions.add(question12);
            questions.add(question13);
            questions.add(question14);
            return questions;
        }

        public List<String> answerToList() {
            List<String> answers = new ArrayList<>();
            answers.add(answer1);
            answers.add(answer2);
            answers.add(answer3);
            answers.add(answer4);
            answers.add(answer5);
            answers.add(answer6);
            answers.add(answer7);
            answers.add(answer8);
            answers.add(answer9);
            answers.add(answer10);
            answers.add(answer11);
            answers.add(answer12);
            answers.add(answer13);
            answers.add(answer14);
            return answers;
        }
    }
}
