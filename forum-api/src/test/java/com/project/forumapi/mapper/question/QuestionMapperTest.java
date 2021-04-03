package com.project.forumapi.mapper.question;

import com.project.forumapi.model.Question;
import com.project.forumapi.model.request.QuestionRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class QuestionMapperTest {

    @InjectMocks
    QuestionMapper questionMapper;

    @Test
    public void shouldConvertCorrectly() {
        QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.setTitle("JavaScript Error!");
        questionRequest.setText("Help");

        Question response = questionMapper.convert(questionRequest);

        Assert.assertEquals(questionRequest.getText(), response.getText());
        Assert.assertEquals(questionRequest.getTitle(), response.getTitle());
    }

}
