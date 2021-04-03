package com.project.forumapi.mapper.question;

import com.project.forumapi.model.Question;
import com.project.forumapi.model.QuestionStatus;
import com.project.forumapi.model.User;
import com.project.forumapi.model.response.AnswerResponse;
import com.project.forumapi.model.response.QuestionResponse;
import com.project.forumapi.service.answer.FindAnswerByQuestionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class QuestionResponseMapperTest {

    @InjectMocks
    QuestionResponseMapper questionResponseMapper;

    @Mock
    FindAnswerByQuestionService findAnswerByQuestionService;

    @Test
    public void shouldConvertCorrectly() {
        User user = new User();
        user.setId(1L);
        user.setName("pedro");
        user.setEmail("email");

        Question question = new Question();
        question.setQuestionStatus(QuestionStatus.SOLVED);
        question.setText("Text");
        question.setTitle("Title");
        question.setId(1L);
        question.setUser(user);
        question.setCreatedAt(LocalDateTime.now().minusMinutes(10));
        question.setUpdatedAt(LocalDateTime.now().minusMinutes(10));

        List<AnswerResponse> answerList = new ArrayList<>();
        answerList.add(new AnswerResponse());

        Mockito.when(findAnswerByQuestionService.find(Mockito.anyLong())).thenReturn(answerList);

        QuestionResponse questionResponse = questionResponseMapper.convert(question);

        Assert.assertEquals(question.getQuestionStatus(), questionResponse.getQuestionStatus());
        Assert.assertEquals(question.getId(), questionResponse.getId());
        Assert.assertEquals(question.getText(), questionResponse.getText());
        Assert.assertEquals(question.getUser().getId(), questionResponse.getUserId());
        Assert.assertEquals(question.getTitle(), questionResponse.getTitle());
        Assert.assertEquals(question.getCreatedAt(), questionResponse.getCreatedAt());
        Assert.assertEquals(question.getUpdatedAt(), questionResponse.getUpdatedAt());
        Assert.assertEquals(question.getUser().getName(), questionResponse.getName());
        Assert.assertEquals(question.getUser().getUsername(), questionResponse.getUsername());
        Assert.assertEquals(java.util.Optional.of(answerList.size()).get(), questionResponse.getNumberOfAwnswers());
    }
}
