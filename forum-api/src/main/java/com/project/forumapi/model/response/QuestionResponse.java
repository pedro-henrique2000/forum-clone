package com.project.forumapi.model.response;

import com.project.forumapi.model.QuestionStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
public class QuestionResponse {

    private Long id;
    private String title;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;
    private Long userId;
    private String username;
    private String name;
    private Integer numberOfAwnswers;

}
