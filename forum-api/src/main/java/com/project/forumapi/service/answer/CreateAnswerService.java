package com.project.forumapi.service.answer;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.mapper.answer.AnswerMapper;
import com.project.forumapi.mapper.answer.AnswerResponseMapper;
import com.project.forumapi.model.Answer;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.AnswerRequest;
import com.project.forumapi.model.response.AnswerResponse;
import com.project.forumapi.repository.AnswerRepository;
import com.project.forumapi.repository.QuestionRepository;
import com.project.forumapi.service.user.LoggedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    LoggedUserService loggedUserService;

    @Autowired
    AnswerResponseMapper answerResponseMapper;

    public AnswerResponse create(Long id, AnswerRequest request) {
        User loggedUser = loggedUserService.getLoggedUser();
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found question with id %d", id)));

        Answer mappedAnswer = answerMapper.convert(request);
        mappedAnswer.setQuestion(question);
        mappedAnswer.setUser(loggedUser);
        return answerResponseMapper.convert(answerRepository.save(mappedAnswer));

    }
}
