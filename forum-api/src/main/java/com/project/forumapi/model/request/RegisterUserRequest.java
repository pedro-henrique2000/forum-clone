package com.project.forumapi.model.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    @Email
    private String email;
    @Size(max = 20)
    private String name;
    @NotBlank(message = "Not Valid Password")
    private String password;
    @NotBlank(message = "Not Valid Password")
    private String confirmPassword;
    @JsonFormat(pattern = "yyyy/MM/dd", shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;

}
