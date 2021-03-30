package com.project.forumapi.controller;

import com.project.forumapi.model.request.QuestionRequest;
import com.project.forumapi.model.response.QuestionResponse;
import com.project.forumapi.service.question.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
public class QuestionController {

    @Autowired
    CreateQuestionService createQuestionService;

    @Autowired
    FindQuestionByIdService findQuestionByIdService;

    @Autowired
    FindQuestionByUserService findQuestionByUserService;

    @Autowired
    DeleteQuestionService deleteQuestionService;

    @Autowired
    UpdateQuestionService updateQuestionService;

    @Autowired
    FinishQuestionService finishQuestionService;

    @PostMapping("/question/create")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionResponse create(@Valid @RequestBody QuestionRequest request) {
        return createQuestionService.create(request);
    }

    @GetMapping("/question/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuestionResponse find(@PathVariable Long id) {
        return findQuestionByIdService.findById(id);
    }

    @GetMapping("/question/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionResponse> findByUser(@PathVariable Long id) {
        return findQuestionByUserService.findByUser(id);
    }

    @DeleteMapping("/question/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void delete(@PathVariable Long id) {
        deleteQuestionService.delete(id);
    }

    @PutMapping("/question/{id}")
    public QuestionResponse update(@Valid @RequestBody QuestionRequest questionRequest, @PathVariable Long id) {
        return updateQuestionService.update(questionRequest, id);
    }

    @PostMapping("/question/finish/{id}")
    public void finish(@PathVariable Long id) {
        finishQuestionService.finish(id);
    }
}
