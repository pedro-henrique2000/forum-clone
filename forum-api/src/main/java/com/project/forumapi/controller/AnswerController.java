package com.project.forumapi.controller;

import com.project.forumapi.model.request.AnswerRequest;
import com.project.forumapi.model.response.AnswerResponse;
import com.project.forumapi.service.answer.CreateAnswerService;
import com.project.forumapi.service.answer.FindAnswerByIdService;
import com.project.forumapi.service.answer.FindAnswerByQuestionService;
import com.project.forumapi.service.answer.UpdateAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    CreateAnswerService createAnswerService;

    @Autowired
    FindAnswerByIdService findAnswerByIdService;

    @Autowired
    FindAnswerByQuestionService findAnswerByQuestionService;

    @Autowired
    UpdateAnswerService updateAnswerService;

    @PostMapping("/answer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerResponse create(@PathVariable Long id, @Valid @RequestBody AnswerRequest request) {
        return createAnswerService.create(id, request);
    }

    @GetMapping("/answer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnswerResponse findById(@PathVariable Long id) {
        return findAnswerByIdService.findById(id);
    }

    @GetMapping("/answer/question/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AnswerResponse> findByQuestion(@PathVariable Long id) {
        return findAnswerByQuestionService.find(id);
    }

    @PutMapping("/answer/{id}")
    public AnswerResponse update(@RequestBody AnswerRequest answerRequest, @PathVariable Long id) {
        return updateAnswerService.update(answerRequest, id);
    }

}
