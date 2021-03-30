package com.project.forumapi.validator.user;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.model.request.RegisterUserRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class MinAgeValidator {

    private static final int MIN_AGE = 18;

    public void accept(RegisterUserRequest request) {
        int age = Period.between(request.getBirthDate(), LocalDate.now()).getYears();

        if(MIN_AGE > age)
            throw new InvalidException(String.format("You must be older than %d to register in that site!", MIN_AGE));

    }

}
