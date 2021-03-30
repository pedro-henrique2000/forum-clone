package com.project.forumapi.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AnswerRequest {
    @NotBlank(message = "Not valid Answer!")
    private String text;
}
