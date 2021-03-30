package com.project.forumapi.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnswerResponse {

    private Long id;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long questionId;
    private Long userId;
    private String username;
    private String name;
    private Integer likeVotes;

}
