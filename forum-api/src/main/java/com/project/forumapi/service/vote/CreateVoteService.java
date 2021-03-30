package com.project.forumapi.service.vote;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.mapper.vote.VoteMapper;
import com.project.forumapi.model.Answer;
import com.project.forumapi.model.User;
import com.project.forumapi.model.Vote;
import com.project.forumapi.model.request.VoteRequest;
import com.project.forumapi.repository.AnswerRepository;
import com.project.forumapi.repository.VoteRepository;
import com.project.forumapi.service.user.LoggedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateVoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    LoggedUserService loggedUserService;

    @Autowired
    VoteMapper voteMapper;

    public void createVote(VoteRequest voteRequest) {
        Answer answer = answerRepository.findById(voteRequest.getPostId()).orElseThrow(NotFoundException::new);
        User user = loggedUserService.getLoggedUser();
        Optional<Vote> voteOptional = voteRepository.findByAnswerAndUser(answer, user);

        //change logic

        if (voteOptional.isPresent() && voteOptional.get().getVoteType().name().equals(voteRequest.getVoteType().name()))
            throw new InvalidException("You already voted in that answer!");

        if (voteRequest.getVoteType().name().equals("LIKE"))
            answer.setVotes(answer.getVotes() + 1);

        if (voteRequest.getVoteType().name().equals("DISLIKE"))
            answer.setVotes(answer.getVotes() + -1);

        answerRepository.save(answer);

        voteRepository.save(voteMapper.convert(voteRequest, answer, user));

    }

}
