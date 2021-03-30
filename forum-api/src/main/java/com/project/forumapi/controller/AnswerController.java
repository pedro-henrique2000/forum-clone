package com.project.forumapi.controller;

import com.project.forumapi.model.request.AnswerRequest;
import com.project.forumapi.model.response.AnswerResponse;
import com.project.forumapi.service.answer.CreateAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AnswerController {

    @Autowired
    CreateAnswerService createAnswerService;

    @PostMapping("/answer/{id}")
    public AnswerResponse create(@PathVariable Long id, @Valid @RequestBody AnswerRequest request) {
        return createAnswerService.create(id, request);
    }

}
