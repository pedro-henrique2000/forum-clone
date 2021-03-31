package com.project.forumapi.validator.answer;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.model.Answer;
import com.project.forumapi.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AnswerPertainToUserValidatorTest {

    @InjectMocks
    AnswerPertainToUserValidator answerPertainToUserValidator;

    @Test
    public void shouldPassWhenAnswerPertainToUser() {
        User user = new User();
        Answer answer = new Answer();
        user.setId(1L);
        answer.setUser(user);
        answerPertainToUserValidator.accept(user, answer);
    }

    @Test(expected = InvalidException.class)
    public void shoudlNotPassWhenAnswerDontPertainToUser() {
        User user = new User();
        User user2 = new User();
        Answer answer = new Answer();
        user.setId(1L);
        user2.setId(2L);
        answer.setUser(user2);
        try{
            answerPertainToUserValidator.accept(user, answer);
        } catch (InvalidException invalidException) {
            Assert.assertEquals("That answer isn't yours!", invalidException.getMessage());
            throw invalidException;
        }

    }

}
