package com.project.forumapi.controller;

import com.project.forumapi.model.request.RegisterUserRequest;
import com.project.forumapi.model.response.UserResponse;
import com.project.forumapi.service.user.FindAllUsersService;
import com.project.forumapi.service.user.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController("")
public class UserController {

    @Autowired
    RegisterUserService registerUserService;

    @Autowired
    FindAllUsersService findAllUsersService;

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterUserRequest request) {
        return registerUserService.register(request);
    }

    @GetMapping("/user")
    public List<UserResponse> findAll() {
        return findAllUsersService.findAll();
    }

}
