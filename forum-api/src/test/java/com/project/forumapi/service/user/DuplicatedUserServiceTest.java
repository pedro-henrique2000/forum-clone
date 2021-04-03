package com.project.forumapi.service.user;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.RegisterUserRequest;
import com.project.forumapi.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DuplicatedUserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    DuplicatedUserService duplicatedUserService;

    @Test
    public void shouldPassWhenEmailIsNotDuplicated() {
        String email = "pedro@teste.com";
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail(email);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        duplicatedUserService.accept(request);
    }

    @Test(expected = InvalidException.class)
    public void shouldNotPassWhenEmailIsDuplicated() {
        String email = "pedro@teste.com";
        User registeredUser = new User();
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail(email);

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(registeredUser));

        try {
            duplicatedUserService.accept(request);
        } catch (InvalidException invalidException) {
            Assert.assertEquals(String.format("Email %s in use!", email), invalidException.getMessage());
            throw invalidException;
        }
    }

}
