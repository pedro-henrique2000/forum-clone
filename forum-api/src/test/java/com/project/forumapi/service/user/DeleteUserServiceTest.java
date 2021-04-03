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
public class DeleteUserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    DeleteUserService deleteUserService;

    @Test
    public void shouldDeleteUserWithSuccess() {
        Long id = 1L;
        User user = new User();
        user.setId(id);

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));

        deleteUserService.delete(id);

        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailWhenUserNotFound() {
        Long id = Mockito.anyLong();
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        try {
            deleteUserService.delete(id);
        } catch(NotFoundException exception) {
            Mockito.verify(userRepository, Mockito.never()).delete(Mockito.any());
            Assert.assertEquals(String.format("Not found user with id %d", id), exception.getMessage());

            throw exception;
        }
    }
}
