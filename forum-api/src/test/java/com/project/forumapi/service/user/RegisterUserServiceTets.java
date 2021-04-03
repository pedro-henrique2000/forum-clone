package com.project.forumapi.service.user;

import com.project.forumapi.mapper.user.UserMapper;
import com.project.forumapi.mapper.user.UserResponseMapper;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.RegisterUserRequest;
import com.project.forumapi.model.response.UserResponse;
import com.project.forumapi.repository.UserRepository;
import com.project.forumapi.validator.user.CorrectPasswordValidator;
import com.project.forumapi.validator.user.MinAgeValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RegisterUserServiceTets {

    @InjectMocks
    RegisterUserService registerUserService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapper userMapper;

    @Mock
    UserResponseMapper userResponseMapper;

    @Mock
    CorrectPasswordValidator correctPasswordValidator;

    @Mock
    MinAgeValidator minAgeValidator;

    @Mock
    DuplicatedUserService duplicatedUserService;

    @Test
    public void shouldRegisterUserWithSuccess() {
        RegisterUserRequest request = new RegisterUserRequest();
        User user = new User();
        User savedUser = new User();
        UserResponse userResponse = new UserResponse();
        userResponse.setId(1L);
        Long expectedId = 1L;

        Mockito.when(userMapper.convert(request)).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(savedUser);
        Mockito.when(userResponseMapper.convert(savedUser)).thenReturn(userResponse);

        UserResponse actualResponse = registerUserService.register(request);

        Mockito.verify(duplicatedUserService, Mockito.times(1)).accept(request);
        Mockito.verify(minAgeValidator, Mockito.times(1)).accept(request);
        Mockito.verify(correctPasswordValidator, Mockito.times(1)).accept(request);

        Assert.assertEquals(expectedId, actualResponse.getId());

    }

}
