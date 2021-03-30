package com.project.forumapi.mapper.question;

import com.project.forumapi.model.Question;
import com.project.forumapi.model.response.QuestionResponse;
import com.project.forumapi.service.answer.FindAnswerByQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionResponseMapper {

    @Autowired
    FindAnswerByQuestionService findAnswerByQuestionService;

    public QuestionResponse convert(Question question) {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setQuestionStatus(question.getQuestionStatus());
        questionResponse.setText(question.getText());
        questionResponse.setTitle(question.getTitle());
        questionResponse.setId(question.getId());
        questionResponse.setCreatedAt(question.getCreatedAt());
        questionResponse.setUpdatedAt(question.getUpdatedAt());
        questionResponse.setUserId(question.getUser().getId());
        questionResponse.setUsername(question.getUser().getUsername());
        questionResponse.setName(question.getUser().getName());
        questionResponse.setNumberOfAwnswers(findAnswerByQuestionService.find(question).size());

        return questionResponse;
    }

}
