package com.project.forumapi.service.user;

import com.project.forumapi.mapper.user.UserMapper;
import com.project.forumapi.mapper.user.UserResponseMapper;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.RegisterUserRequest;
import com.project.forumapi.model.response.UserResponse;
import com.project.forumapi.repository.UserRepository;
import com.project.forumapi.validator.user.CorrectPasswordValidator;
import com.project.forumapi.validator.user.MinAgeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserResponseMapper userResponseMapper;

    @Autowired
    MinAgeValidator minAgeValidator;

    @Autowired
    CorrectPasswordValidator correctPasswordValidator;

    @Autowired
    DuplicatedUserService duplicatedUserService;

    public UserResponse register(RegisterUserRequest request) {
        duplicatedUserService.accept(request);
        minAgeValidator.accept(request);
        correctPasswordValidator.accept(request);
        User user = userMapper.convert(request);
        User savedUser = userRepository.save(user);

        return userResponseMapper.convert(savedUser);
    }

}
