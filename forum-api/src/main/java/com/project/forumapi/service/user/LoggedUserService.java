package com.project.forumapi.service.user;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoggedUserService {

    @Autowired
    SearchUserByEmailService service;

    public User getLoggedUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(User.class::cast)
                .map(User::getUsername)
                .map(service::find)
                .orElseThrow(() -> new NotFoundException("Not found"));
    }

}
