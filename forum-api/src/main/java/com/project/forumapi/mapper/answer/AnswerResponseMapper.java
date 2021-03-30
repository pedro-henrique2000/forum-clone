package com.project.forumapi.mapper.answer;

import com.project.forumapi.model.Answer;
import com.project.forumapi.model.response.AnswerResponse;
import org.springframework.stereotype.Component;

@Component
public class AnswerResponseMapper {

    public AnswerResponse convert(Answer answer) {
        AnswerResponse answerResponse = new AnswerResponse();
        answerResponse.setId(answer.getId());
        answerResponse.setText(answer.getText());
        answerResponse.setCreatedAt(answer.getCreatedAt());
        answerResponse.setUpdatedAt(answer.getUpdatedAt());
        answerResponse.setName(answer.getUser().getName());
        answerResponse.setUserId(answer.getUser().getId());
        answerResponse.setUsername(answer.getUser().getUsername());
        answerResponse.setQuestionId(answer.getQuestion().getId());
        answerResponse.setLikeVotes(answer.getVotes());

        return answerResponse;
    }

}
