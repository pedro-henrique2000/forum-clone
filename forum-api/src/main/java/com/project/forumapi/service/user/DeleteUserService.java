package com.project.forumapi.service.user;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserService {

    @Autowired
    UserRepository repository;

    public void delete(Long id) {
        repository.delete(repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Not found user with id %d", id))));
    }

}
