package com.project.forumapi.validator.question;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class QuestionPertainToUserValidatorTest {

    @InjectMocks
    QuestionPertainToUserValidator questionPertainToUserValidator;

    @Test
    public void shoudlPassWhenQuestionPertainToUser() {
        User user = new User();
        user.setId(1L);
        Question question = new Question();
        question.setUser(user);

        questionPertainToUserValidator.accept(user, question);
    }

    @Test(expected = InvalidException.class)
    public void shouldNotPassWhenQuestionDontPertainToUser() {
        User user = new User();
        User user2 = new User();
        Question question = new Question();

        user.setId(1L);
        user2.setId(2L);
        question.setUser(user2);

        try {
            questionPertainToUserValidator.accept(user, question);
        } catch(InvalidException invalidException) {
            Assert.assertEquals("That Question isn't yours!", invalidException.getMessage());
            throw invalidException;
        }
    }

}
