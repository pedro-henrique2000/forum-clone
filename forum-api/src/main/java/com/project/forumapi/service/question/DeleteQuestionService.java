package com.project.forumapi.service.question;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.repository.QuestionRepository;
import com.project.forumapi.service.user.LoggedUserService;
import com.project.forumapi.validator.question.QuestionPertainToUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteQuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    LoggedUserService loggedUserService;

    @Autowired
    QuestionPertainToUserValidator questionPertainToUserValidator;

    public void delete(Long id) {
        User loggedUser = loggedUserService.getLoggedUser();
        Question question = questionRepository.findById(id).orElseThrow(NotFoundException::new);
        questionPertainToUserValidator.accept(loggedUser, question);
        questionRepository.delete(question);
    }

}
