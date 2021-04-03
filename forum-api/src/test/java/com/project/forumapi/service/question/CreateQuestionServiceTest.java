package com.project.forumapi.service.question;

import com.project.forumapi.mapper.question.QuestionMapper;
import com.project.forumapi.mapper.question.QuestionResponseMapper;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.QuestionRequest;
import com.project.forumapi.model.response.QuestionResponse;
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

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CreateQuestionServiceTest {

    @Mock
    LoggedUserService loggedUserService;

    @InjectMocks
    CreateQuestionService createQuestionService;

    @Mock
    QuestionRepository questionRepository;

    @Mock
    QuestionMapper questionMapper;

    @Mock
    QuestionResponseMapper questionResponseMapper;

    @Test
    public void shouldCreateWithSuccess() {
        User user = new User();
        user.setId(1L);

        QuestionRequest questionRequest = new QuestionRequest();

        Question question = new Question();

        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setUserId(user.getId());

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(questionMapper.convert(questionRequest)).thenReturn(question);
        Mockito.when(questionRepository.save(question)).thenReturn(question);
        Mockito.when(questionResponseMapper.convert(question)).thenReturn(questionResponse);

        QuestionResponse response = createQuestionService.create(questionRequest);

        Assert.assertNotNull(response);
        Assert.assertEquals(questionResponse.getUserId(), user.getId());

    }

}
