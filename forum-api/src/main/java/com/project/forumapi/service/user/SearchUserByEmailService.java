package com.project.forumapi.service.user;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.model.User;
import com.project.forumapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchUserByEmailService {

    @Autowired
    UserRepository userRepository;

    public User find(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format("Not found a user with email %s", email)));
    }
}
