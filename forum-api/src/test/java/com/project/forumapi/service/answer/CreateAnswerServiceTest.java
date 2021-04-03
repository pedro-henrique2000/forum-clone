package com.project.forumapi.service.answer;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.mapper.answer.AnswerMapper;
import com.project.forumapi.mapper.answer.AnswerResponseMapper;
import com.project.forumapi.mapper.question.QuestionResponseMapper;
import com.project.forumapi.model.Answer;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.AnswerRequest;
import com.project.forumapi.model.response.AnswerResponse;
import com.project.forumapi.repository.AnswerRepository;
import com.project.forumapi.repository.QuestionRepository;
import com.project.forumapi.service.user.LoggedUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CreateAnswerServiceTest {

    @Mock
    AnswerRepository answerRepository;

    @InjectMocks
    CreateAnswerService createAnswerService;

    @Mock
    LoggedUserService loggedUserService;

    @Mock
    AnswerMapper answerMapper;

    @Mock
    QuestionRepository questionRepository;

    @Mock
    AnswerResponseMapper answerResponseMapper;

    @Test
    public void shouldCreateAnswerWithSuccess() {
        Long id = 1L;

        User user = new User();

        Question question = new Question();

        AnswerRequest answerRequest = new AnswerRequest();
        Answer answer = new Answer();
        AnswerResponse answerResponse = new AnswerResponse();

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(questionRepository.findById(id)).thenReturn(Optional.of(question));
        Mockito.when(answerMapper.convert(answerRequest)).thenReturn(answer);
        Mockito.when(answerRepository.save(answer)).thenReturn(answer);
        Mockito.when(answerResponseMapper.convert(answer)).thenReturn(answerResponse);

        AnswerResponse result = createAnswerService.create(id, answerRequest);

        Assert.assertNotNull(result);
        Mockito.verify(answerMapper, Mockito.times(1)).convert(answerRequest);
        Mockito.verify(answerResponseMapper, Mockito.times(1)).convert(answer);
        Mockito.verify(answerRepository, Mockito.times(1)).save(answer);
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailWhenQuestionNotFound() {
        Long id = 1L;

        User user = new User();

        Question question = new Question();

        AnswerRequest answerRequest = new AnswerRequest();
        Answer answer = new Answer();
        AnswerResponse answerResponse = new AnswerResponse();

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(questionRepository.findById(id)).thenReturn(Optional.empty());

        try {
            createAnswerService.create(id, answerRequest);
        } catch (NotFoundException exception) {
            Mockito.verify(answerMapper, Mockito.times(0)).convert(answerRequest);
            Mockito.verify(answerResponseMapper, Mockito.times(0)).convert(answer);
            Mockito.verify(answerRepository, Mockito.times(0)).save(answer);
            throw exception;
        }
    }

}
