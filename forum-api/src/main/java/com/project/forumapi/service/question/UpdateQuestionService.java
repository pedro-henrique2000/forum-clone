package com.project.forumapi.service.question;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.mapper.question.QuestionResponseMapper;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.QuestionRequest;
import com.project.forumapi.model.response.QuestionResponse;
import com.project.forumapi.repository.QuestionRepository;
import com.project.forumapi.service.user.LoggedUserService;
import com.project.forumapi.validator.question.QuestionPertainToUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateQuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionResponseMapper questionResponseMapper;

    @Autowired
    LoggedUserService loggedUserService;

    @Autowired
    QuestionPertainToUserValidator questionPertainToUserValidator;

    public QuestionResponse update(QuestionRequest questionRequest, Long id) {
        User user = loggedUserService.getLoggedUser();
        Question question = questionRepository.findById(id).orElseThrow(NotFoundException::new);
        questionPertainToUserValidator.accept(user, question);
        question.setTitle(questionRequest.getTitle());
        question.setText(questionRequest.getText());

        return questionResponseMapper.convert(questionRepository.save(question));
    }
}
