package com.ohble.domain.question.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class QuestionRequestDto {

    @Getter
    public static class CreateForm {
        @NotBlank
        private String content;
    }

    @Getter
    public static class LoadForm {
        @NotNull
        private Long questionId;
    }
}
