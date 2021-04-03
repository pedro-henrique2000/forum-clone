package com.project.forumapi.service.question;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.mapper.question.QuestionResponseMapper;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.response.QuestionResponse;
import com.project.forumapi.repository.QuestionRepository;
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
public class FindQuestionByIdServiceTest {

    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    FindQuestionByIdService findQuestionByIdService;

    @Mock
    QuestionResponseMapper questionResponseMapper;

    @Test
    public void shouldFoundWithSuccess() {
        Long id = 1L;

        Question question = new Question();
        question.setId(id);

        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(question.getId());

        Mockito.when(questionRepository.findById(id)).thenReturn(Optional.of(question));
        Mockito.when(questionResponseMapper.convert(question)).thenReturn(questionResponse);

        QuestionResponse result = findQuestionByIdService.findById(id);

        Mockito.verify(questionRepository, Mockito.times(1)).findById(id);
        Mockito.verify(questionResponseMapper, Mockito.times(1)).convert(question);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), questionResponse.getId());
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailWhenQuestionNotFound() {
        Mockito.when(questionRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        try {
            QuestionResponse response = findQuestionByIdService.findById(Mockito.any());
        } catch(NotFoundException notFoundException) {
            Mockito.verify(questionResponseMapper, Mockito.times(0)).convert(Mockito.any());
            Mockito.verify(questionRepository, Mockito.times(1)).findById(Mockito.any());

            throw notFoundException;
        }
    }

}
