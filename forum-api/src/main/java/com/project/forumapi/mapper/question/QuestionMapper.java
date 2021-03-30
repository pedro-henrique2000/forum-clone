package com.project.forumapi.mapper.question;

import com.project.forumapi.model.Question;
import com.project.forumapi.model.QuestionStatus;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.QuestionRequest;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    public Question convert(QuestionRequest request) {
        Question question = new Question();
        question.setQuestionStatus(QuestionStatus.UNSOLVED);
        question.setText(request.getText());
        question.setTitle(request.getTitle());

        return question;
    }

}
