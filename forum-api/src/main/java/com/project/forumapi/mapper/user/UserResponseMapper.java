package com.project.forumapi.mapper.user;

import com.project.forumapi.model.User;
import com.project.forumapi.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    public UserResponse convert(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getName());
        userResponse.setBirthDate(user.getBirthDate());
        userResponse.setId(user.getId());
        userResponse.setRegistredDate(user.getRegisteredDate());

        return userResponse;
    }

}
