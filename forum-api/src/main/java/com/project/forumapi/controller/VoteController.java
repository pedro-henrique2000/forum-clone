package com.project.forumapi.controller;

import com.project.forumapi.model.request.VoteRequest;
import com.project.forumapi.service.vote.CreateVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {

    @Autowired
    CreateVoteService createVoteService;

    @PostMapping("answer/vote")
    public void vote(@RequestBody VoteRequest request) {
        createVoteService.createVote(request);
    }

}
