package com.project.forumapi.model.response;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FindAllUserResponse {

    private Long id;
    private String email;
    private String name;
    private LocalDate birthDate;
    private LocalDateTime registeredDate;


}
