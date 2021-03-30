package com.project.forumapi.service.question;

import com.project.forumapi.mapper.question.QuestionMapper;
import com.project.forumapi.mapper.question.QuestionResponseMapper;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.model.request.QuestionRequest;
import com.project.forumapi.model.response.QuestionResponse;
import com.project.forumapi.repository.QuestionRepository;
import com.project.forumapi.service.user.LoggedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateQuestionService {

    @Autowired
    LoggedUserService loggedUserService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    QuestionResponseMapper questionResponseMapper;

    public QuestionResponse create(QuestionRequest request) {
        User loggedUser = loggedUserService.getLoggedUser();
        Question question = questionMapper.convert(request);
        question.setUser(loggedUser);
        return questionResponseMapper.convert(questionRepository.save(question));
    }
}
