package com.project.forumapi.validator.question;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import org.springframework.stereotype.Component;

@Component
public class QuestionPertainToUserValidator {

    public void accept(User user, Question question) {
        if(user.getId() != question.getUser().getId())
            throw new InvalidException("That Question isn't yours!");
    }

}
