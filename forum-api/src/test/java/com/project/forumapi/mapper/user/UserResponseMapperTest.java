package com.project.forumapi.mapper.user;

import com.project.forumapi.model.User;
import com.project.forumapi.model.response.UserResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserResponseMapperTest {

    @InjectMocks
    UserResponseMapper userResponseMapper;

    @Test
    public void shouldConvertCorrectly() {
        User user = new User();
        user.setId(1L);
        user.setEmail("pedro@teste.com");
        user.setBirthDate(LocalDate.now().minusYears(18));
        user.setRegisteredDate(LocalDateTime.now().minusHours(2));
        user.setName("Pedro");

        UserResponse response = userResponseMapper.convert(user);
        Assert.assertEquals(user.getId(), response.getId());
        Assert.assertEquals(user.getEmail(), response.getEmail());
        Assert.assertEquals(user.getName(), response.getName());
        Assert.assertEquals(user.getBirthDate(), response.getBirthDate());
        Assert.assertEquals(user.getRegisteredDate(), response.getRegistredDate());

    }


}
