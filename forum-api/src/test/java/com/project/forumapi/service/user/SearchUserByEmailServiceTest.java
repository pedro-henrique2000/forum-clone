package com.project.forumapi.service.user;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.model.User;
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
public class SearchUserByEmailServiceTest {

    @InjectMocks
    SearchUserByEmailService searchUserByEmailService;

    @Mock
    UserRepository userRepository;

    @Test
    public void shouldFindUserWithSuccess() {
        String email = "pedro@teste.com";

        User user = new User();
        user.setId(1L);
        user.setEmail(email);

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        User result = searchUserByEmailService.find(email);

        Mockito.verify(userRepository, Mockito.times(1)).findByEmail(email);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getEmail(), user.getEmail());
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailWhenUserNotFound() {
        User user = new User();

        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        try {
            User result = searchUserByEmailService.find(Mockito.anyString());
        } catch (NotFoundException exception) {
            Mockito.verify(userRepository, Mockito.times(1)).findByEmail(Mockito.any());
            throw exception;
        }
    }

}
