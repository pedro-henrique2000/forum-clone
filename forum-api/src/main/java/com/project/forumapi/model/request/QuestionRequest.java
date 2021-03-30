package com.project.forumapi.model.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class QuestionRequest {
    @Size(max = 50)
    private String title;
    @Size(max = 300)
    private String text;
}
