package com.ohble.domain.answer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class AnswerRequestDto {

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
