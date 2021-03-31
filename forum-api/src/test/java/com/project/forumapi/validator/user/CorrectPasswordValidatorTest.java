package com.project.forumapi.validator.user;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.model.request.RegisterUserRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CorrectPasswordValidatorTest {

    @InjectMocks
    CorrectPasswordValidator correctPasswordValidator;

    @Test
    public void shouldPassWhenPasswordsAreEquals() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setConfirmPassword("123");
        request.setPassword("123");
        correctPasswordValidator.accept(request);
    }

    @Test(expected = InvalidException.class)
    public void shouldNotPassWhenPasswordsAreNotEquals() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setConfirmPassword("123");
        request.setPassword("12");
        try {
            correctPasswordValidator.accept(request);
        } catch(InvalidException invalidException) {
            Assert.assertEquals("Passwords aren't equals!", invalidException.getMessage());
            throw invalidException;
        }
    }

}
