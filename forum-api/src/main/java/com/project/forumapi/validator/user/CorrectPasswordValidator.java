package com.project.forumapi.validator.user;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.model.request.RegisterUserRequest;
import org.springframework.stereotype.Component;

@Component
public class CorrectPasswordValidator {

    public void accept(RegisterUserRequest request) {
        if(!request.getPassword().equals(request.getConfirmPassword()))
            throw new InvalidException("Passwords aren't equals!");
    }

}
