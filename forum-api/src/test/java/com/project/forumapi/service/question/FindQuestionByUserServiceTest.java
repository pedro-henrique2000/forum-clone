package com.project.forumapi.service.question;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.mapper.question.QuestionResponseMapper;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.model.response.QuestionResponse;
import com.project.forumapi.repository.QuestionRepository;
import com.project.forumapi.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FindQuestionByUserServiceTest {

    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    FindQuestionByUserService findQuestionByUserService;

    @Mock
    QuestionResponseMapper questionResponseMapper;

    @Mock
    UserRepository userRepository;

    @Test
    public void shouldFindAQuestionWithSuccess() {
        Long id = 1L;
        User user = new User();
        user.setId(id);

        Question question1 = new Question();
        Question question2 = new Question();
        question1.setUser(user);
        question2.setUser(user);

        List<Question> questionList = Arrays.asList(question1, question2);

        QuestionResponse questionResponse1 = new QuestionResponse();
        questionResponse1.setUserId(user.getId());
        QuestionResponse questionResponse2 = new QuestionResponse();
        questionResponse2.setUserId(user.getId());

        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Mockito.when(questionRepository.findByUser(user)).thenReturn(questionList);
        Mockito.when(questionResponseMapper.convert(questionList.get(0))).thenReturn(questionResponse1);
        Mockito.when(questionResponseMapper.convert(questionList.get(1))).thenReturn(questionResponse2);

        List<QuestionResponse> result = findQuestionByUserService.findByUser(id);

        Mockito.verify(userRepository, Mockito.times(1)).findById(id);
        Mockito.verify(questionRepository, Mockito.times(1)).findByUser(user);
        Mockito.verify(questionResponseMapper, Mockito.times(result.size())).convert(Mockito.any());

        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(user.getId(), result.get(0).getUserId());
        Assert.assertEquals(user.getId(), result.get(1).getUserId());

    }

    @Test(expected = NotFoundException.class)
    public void shouldFailWhenUserNotFound() {
        Long id = 1L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        try {
            List<QuestionResponse> questionResponses = findQuestionByUserService.findByUser(id);
        } catch (NotFoundException notFoundException) {
            Mockito.verify(userRepository, Mockito.times(1)).findById(id);
            Mockito.verify(questionRepository, Mockito.times(0)).findByUser(Mockito.any());
            Mockito.verify(questionResponseMapper, Mockito.times(0)).convert(Mockito.any());

            throw notFoundException;
        }
    }
}