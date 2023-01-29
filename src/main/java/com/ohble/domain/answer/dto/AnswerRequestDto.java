package com.ohble.domain.answer.dto;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class AnswerRequestDto {

    @Getter
    public static class CreateForm {
        @NotBlank
        private String content;
        @NotNull
        private Long questionId;
    }

    @Getter
    public static class LoadForm {
        @NotNull
        private Long questionId;
    }
}
