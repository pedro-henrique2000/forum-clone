package com.project.forumapi.service.user;

import com.project.forumapi.mapper.user.UserResponseMapper;
import com.project.forumapi.model.User;
import com.project.forumapi.model.response.UserResponse;
import com.project.forumapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllUsersService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserResponseMapper userResponseMapper;

    public List<UserResponse> findAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(userResponseMapper::convert)
                .collect(Collectors.toList());
    }

}
