package com.project.forumapi.mapper.user;

import com.project.forumapi.model.User;
import com.project.forumapi.model.request.RegisterUserRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserMapperTest {

    @InjectMocks
    UserMapper userMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    public void shouldConvertWithSuccess() {
        RegisterUserRequest request = new RegisterUserRequest();
        request.setEmail("pedro@teste.com");
        request.setName("pedro");
        request.setBirthDate(LocalDate.now().minusYears(18));

        User result = userMapper.convert(request);

        Assert.assertEquals(request.getEmail(), result.getEmail());
        Assert.assertEquals(request.getName(), result.getName());
        Assert.assertEquals(request.getBirthDate(), result.getBirthDate());

    }

}
