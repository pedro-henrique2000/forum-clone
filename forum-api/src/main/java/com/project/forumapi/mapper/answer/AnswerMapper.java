package com.project.forumapi.mapper.answer;

import com.project.forumapi.model.Answer;
import com.project.forumapi.model.request.AnswerRequest;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper {

    public Answer convert(AnswerRequest request) {
        Answer answer = new Answer();
        answer.setText(request.getText());

        return answer;
    }

}
