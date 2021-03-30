package com.project.forumapi.service.question;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.QuestionStatus;
import com.project.forumapi.model.User;
import com.project.forumapi.repository.QuestionRepository;
import com.project.forumapi.service.user.LoggedUserService;
import com.project.forumapi.validator.question.QuestionPertainToUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinishQuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    LoggedUserService loggedUserService;

    @Autowired
    QuestionPertainToUserValidator questionPertainToUserValidator;

    public void finish(Long id) {
        User user = loggedUserService.getLoggedUser();
        Question question = questionRepository.findById(id).orElseThrow(NotFoundException::new);
        questionPertainToUserValidator.accept(user, question);

        if(question.getQuestionStatus().name().equals("SOLVED"))
            throw new InvalidException("Question already solved!");

        question.setQuestionStatus(QuestionStatus.SOLVED);
        questionRepository.save(question);
    }

}
