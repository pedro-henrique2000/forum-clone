package com.project.forumapi.service.vote;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.mapper.vote.VoteMapper;
import com.project.forumapi.model.Answer;
import com.project.forumapi.model.User;
import com.project.forumapi.model.Vote;
import com.project.forumapi.model.VoteType;
import com.project.forumapi.model.request.VoteRequest;
import com.project.forumapi.repository.AnswerRepository;
import com.project.forumapi.repository.VoteRepository;
import com.project.forumapi.service.user.LoggedUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CreateVoteServiceTest {

    @InjectMocks
    CreateVoteService createVoteService;

    @Mock
    VoteRepository voteRepository;

    @Mock
    AnswerRepository answerRepository;

    @Mock
    LoggedUserService loggedUserService;

    @Mock
    VoteMapper voteMapper;

    @Test
    public void shouldGiveALikeVoteWithSuccess() {
        Long id = 1L;
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setPostId(id);
        voteRequest.setVoteType(VoteType.LIKE);

        Answer answer = new Answer();
        User user = new User();

        Vote vote = new Vote();

        Mockito.when(answerRepository.findById(id)).thenReturn(Optional.of(answer));
        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(voteRepository.findByAnswerAndUser(answer, user)).thenReturn(Optional.empty());
        Mockito.when(voteMapper.convert(voteRequest, answer, user)).thenReturn(vote);
        Mockito.when(voteRepository.save(vote)).thenReturn(vote);

        Vote result = createVoteService.createVote(voteRequest);

        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(1), answer.getVotes());
        Mockito.verify(answerRepository, Mockito.times(1)).save(answer);
        Mockito.verify(voteRepository, Mockito.times(1)).save(vote);

    }

    @Test
    public void shouldGiveADislikeVoteWithSuccess() {
        Long id = 1L;
        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setPostId(id);
        voteRequest.setVoteType(VoteType.DISLIKE);

        Answer answer = new Answer();
        User user = new User();

        Vote vote = new Vote();

        Mockito.when(answerRepository.findById(id)).thenReturn(Optional.of(answer));
        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(voteRepository.findByAnswerAndUser(answer, user)).thenReturn(Optional.empty());
        Mockito.when(voteMapper.convert(voteRequest, answer, user)).thenReturn(vote);
        Mockito.when(voteRepository.save(vote)).thenReturn(vote);

        Vote result = createVoteService.createVote(voteRequest);

        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(-1), answer.getVotes());
        Mockito.verify(answerRepository, Mockito.times(1)).save(answer);
        Mockito.verify(voteRepository, Mockito.times(1)).save(vote);

    }

    @Test(expected = InvalidException.class)
    public void shouldFailWhenUserAlreadyVoted() {
        Long id = 1L;

        User user = new User();

        Answer answer = new Answer();

        Vote vote = new Vote();
        vote.setVoteType(VoteType.LIKE);

        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setVoteType(VoteType.LIKE);
        voteRequest.setPostId(id);

        Mockito.when(answerRepository.findById(id)).thenReturn(Optional.of(answer));
        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(voteRepository.findByAnswerAndUser(answer, user)).thenReturn(Optional.of(vote));

        try {
            createVoteService.createVote(voteRequest);
        } catch (InvalidException invalidException) {
            Mockito.verify(answerRepository, Mockito.times(0)).save(answer);
            Mockito.verify(voteRepository, Mockito.times(0)).save(vote);
            Assert.assertEquals("You already voted in that answer!", invalidException.getMessage());
            throw invalidException;
        }

    }

    @Test
    public void shouldUpdateAnswerVoteWhenUserChangeHisVote() {
        Long id = 1L;

        User user = new User();

        Answer answer = new Answer();
        answer.setVotes(-1);

        Vote vote = new Vote();
        vote.setAnswer(answer);
        vote.setVoteType(VoteType.DISLIKE);

        VoteRequest voteRequest = new VoteRequest();
        voteRequest.setPostId(id);
        voteRequest.setVoteType(VoteType.LIKE);

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(answerRepository.findById(id)).thenReturn(Optional.of(answer));
        Mockito.when(voteRepository.findByAnswerAndUser(answer, user)).thenReturn(Optional.of(vote));
        Mockito.when(voteRepository.save(vote)).thenReturn(vote);

        Vote result = createVoteService.createVote(voteRequest);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getVoteType(), voteRequest.getVoteType());
        Assert.assertEquals(Integer.valueOf(1), answer.getVotes());
        Mockito.verify(voteRepository, Mockito.times(1)).save(vote);

    }

}
