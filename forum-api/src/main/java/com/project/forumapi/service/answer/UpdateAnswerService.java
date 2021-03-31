package com.project.forumapi.service.answer;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.mapper.answer.AnswerResponseMapper;
import com.project.forumapi.model.Answer;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.AnswerRequest;
import com.project.forumapi.model.response.AnswerResponse;
import com.project.forumapi.repository.AnswerRepository;
import com.project.forumapi.service.user.LoggedUserService;
import com.project.forumapi.validator.answer.AnswerPertainToUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateAnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    AnswerResponseMapper answerResponseMapper;

    @Autowired
    LoggedUserService loggedUserService;

    @Autowired
    AnswerPertainToUserValidator answerPertainToUserValidator;

    public AnswerResponse update(AnswerRequest answerRequest, Long id) {
        User user = loggedUserService.getLoggedUser();
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found an answer with id %d", id)));

        answerPertainToUserValidator.accept(user, answer);
        answer.setText(answerRequest.getText());
        return answerResponseMapper.convert(answerRepository.save(answer));
    }

}
