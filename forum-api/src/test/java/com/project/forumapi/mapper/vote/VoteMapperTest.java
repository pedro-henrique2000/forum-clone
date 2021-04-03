package com.project.forumapi.mapper.vote;

import com.project.forumapi.model.Answer;
import com.project.forumapi.model.User;
import com.project.forumapi.model.Vote;
import com.project.forumapi.model.VoteType;
import com.project.forumapi.model.request.VoteRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class VoteMapperTest {

    @InjectMocks
    VoteMapper voteMapper;

    @Test
    public void shouldConvertCorrectly() {
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setPostId(1L);
        voteRequest.setVoteType(VoteType.LIKE);
        Answer answer = new Answer();
        answer.setId(1L);
        User user = new User();

        Vote vote = voteMapper.convert(voteRequest, answer, user);

        Assert.assertEquals(voteRequest.getVoteType(), vote.getVoteType());
        Assert.assertEquals(voteRequest.getPostId(), vote.getAnswer().getId());
    }

}
