package com.project.forumapi.service.user;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.RegisterUserRequest;
import com.project.forumapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DuplicatedUserService {

    @Autowired
    UserRepository userRepository;

    public void accept(RegisterUserRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());

        if(optionalUser.isPresent())
            throw new InvalidException(String.format("Email %s in use!", request.getEmail()));
    }

}
