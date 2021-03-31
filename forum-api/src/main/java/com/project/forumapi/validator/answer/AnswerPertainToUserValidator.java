package com.project.forumapi.validator.answer;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.model.Answer;
import com.project.forumapi.model.User;
import org.springframework.stereotype.Component;

@Component
public class AnswerPertainToUserValidator {

    public void accept(User user, Answer answer) {
        if(user.getId() != answer.getUser().getId())
            throw new InvalidException("That answer isn't yours!");
    }

}
