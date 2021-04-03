package com.project.forumapi.mapper.answer;

import com.project.forumapi.model.Answer;
import com.project.forumapi.model.request.AnswerRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class AnswerMapperTest {

    @InjectMocks
    AnswerMapper answerMapper;

    @Test
    public void shouldConvertCorrectly() {
        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setText("Yes!");

        Answer result = answerMapper.convert(answerRequest);

        Assert.assertEquals(answerRequest.getText(), result.getText());
    }

}
