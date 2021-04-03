package com.project.forumapi.service.user;

import com.project.forumapi.mapper.user.UserResponseMapper;
import com.project.forumapi.model.User;
import com.project.forumapi.model.response.UserResponse;
import com.project.forumapi.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FindAllUserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserResponseMapper userResponseMapper;

    @InjectMocks
    FindAllUsersService findAllUsersService;

    @Test
    public void shouldReturnAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("pedro1");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("pedro2");

        List<User> list = new ArrayList();
        list.add(user1);
        list.add(user2);

        UserResponse userResponse1 = new UserResponse();
        userResponse1.setName(user1.getName());
        userResponse1.setId(user1.getId());

        UserResponse userResponse2 = new UserResponse();
        userResponse2.setName(user2.getName());
        userResponse2.setId(user2.getId());

        Mockito.when(userRepository.findAll()).thenReturn(list);
        Mockito.when(userResponseMapper.convert(user1)).thenReturn(userResponse1);
        Mockito.when(userResponseMapper.convert(user2)).thenReturn(userResponse2);

        List<UserResponse> result = findAllUsersService.findAll();

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0).getId(), user1.getId());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Mockito.verify(userResponseMapper, Mockito.times(2)).convert(Mockito.any());
    }

}
