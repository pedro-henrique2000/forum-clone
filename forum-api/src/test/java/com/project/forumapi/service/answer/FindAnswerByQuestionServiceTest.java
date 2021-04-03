package com.project.forumapi.service.answer;

import com.project.forumapi.mapper.answer.AnswerResponseMapper;
import com.project.forumapi.model.Answer;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.response.AnswerResponse;
import com.project.forumapi.repository.AnswerRepository;
import com.project.forumapi.repository.QuestionRepository;
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
public class FindAnswerByQuestionServiceTest {

    @Mock
    AnswerRepository answerRepository;

    @InjectMocks
    FindAnswerByQuestionService findAnswerByQuestionService;

    @Mock
    AnswerResponseMapper answerResponseMapper;

    @Mock
    QuestionRepository questionRepository;

    @Test
    public  void shouldFindWithSuccess() {
        Long id = 1L;
        Question question = new Question();

        Answer answer1 = new Answer();
        Answer answer2 = new Answer();
        List<Answer> list = Arrays.asList(answer1, answer2);
        AnswerResponse answerResponse1 = new AnswerResponse();
        AnswerResponse answerResponse2 = new AnswerResponse();

        Mockito.when(questionRepository.findById(id)).thenReturn(Optional.of(question));
        Mockito.when(answerRepository.findByQuestion(question)).thenReturn(list);
        Mockito.when(answerResponseMapper.convert(list.get(0))).thenReturn(answerResponse1);
        Mockito.when(answerResponseMapper.convert(list.get(1))).thenReturn(answerResponse2);

        List<AnswerResponse> result = findAnswerByQuestionService.find(id);

        Assert.assertNotNull(result);
        Assert.assertEquals(2, result.size());
        Mockito.verify(answerResponseMapper, Mockito.times(2)).convert(Mockito.any());

    }

}
