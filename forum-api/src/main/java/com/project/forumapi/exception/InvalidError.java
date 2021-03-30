package com.project.forumapi.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvalidError {

    private LocalDateTime timestamp;
    private Integer statusValue;
    private String message;
    private String path;
    private String error;

}
