package com.project.forumapi.mapper.user;

import com.project.forumapi.model.User;
import com.project.forumapi.model.request.RegisterUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper {

    @Autowired
    PasswordEncoder passwordEncoder;

    public User convert(RegisterUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBirthDate(request.getBirthDate());

        return user;
    }

}
