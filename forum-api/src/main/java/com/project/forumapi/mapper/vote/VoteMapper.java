package com.project.forumapi.mapper.vote;

import com.project.forumapi.model.Answer;
import com.project.forumapi.model.User;
import com.project.forumapi.model.Vote;
import com.project.forumapi.model.request.VoteRequest;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {

    public Vote convert(VoteRequest request, Answer answer, User user) {
        Vote vote = new Vote();
        vote.setVoteType(request.getVoteType());
        vote.setAnswer(answer);
        vote.setUser(user);

        return vote;
    }

}
