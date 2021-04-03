package com.project.forumapi.mapper.answer;

import com.project.forumapi.model.Answer;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.model.response.AnswerResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AnswerResponseMapperTest {

    @InjectMocks
    AnswerResponseMapper answerResponseMapper;

    @Test
    public void shouldConvertCorrectly() {
        User user = new User();
        user.setId(1L);

        Question question = new Question();
        question.setUser(user);
        question.setId(1L);

        Answer answer = new Answer();
        answer.setText("Any Solutuon");
        answer.setVotes(0);
        answer.setCreatedAt(LocalDateTime.now().minusMinutes(5));
        answer.setUpdatedAt(LocalDateTime.now().minusMinutes(5));
        answer.setId(1L);
        answer.setUser(user);
        answer.setQuestion(question);

        AnswerResponse result = answerResponseMapper.convert(answer);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getId(), answer.getId());
        Assert.assertEquals(result.getCreatedAt(), answer.getCreatedAt());
        Assert.assertEquals(result.getLikeVotes(), answer.getVotes());
        Assert.assertEquals(result.getText(), answer.getText());
        Assert.assertEquals(result.getQuestionId(), answer.getQuestion().getId());
        Assert.assertEquals(result.getUserId(), answer.getUser().getId());
    }
}
