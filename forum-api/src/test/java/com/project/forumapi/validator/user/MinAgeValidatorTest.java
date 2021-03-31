package com.project.forumapi.validator.user;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.model.request.RegisterUserRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MinAgeValidatorTest {

    @InjectMocks
    MinAgeValidator minAgeValidator;

    @Test
    public void shouldPassWhenUserIsOlderThanMinAge() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setBirthDate(LocalDate.now().minusYears(18));
        minAgeValidator.accept(request);
    }

    @Test(expected = InvalidException.class)
    public void shouldNotPassWhenUserIsNotOlderThanMinAge() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setBirthDate(LocalDate.now().minusYears(5));
        try {
            minAgeValidator.accept(request);
        } catch(InvalidException invalidException) {
            Assert.assertEquals("You must be older than 18 to register in that site!", invalidException.getMessage());
            throw invalidException;
        }
    }

}
