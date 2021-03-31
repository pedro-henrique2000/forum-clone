package com.project.forumapi.service.question;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.mapper.question.QuestionResponseMapper;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.model.response.QuestionResponse;
import com.project.forumapi.repository.QuestionRepository;
import com.project.forumapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindQuestionByUserService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionResponseMapper questionResponseMapper;

    public List<QuestionResponse> findByUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found a user with id %d", id)));
        List<Question> questionList = questionRepository.findByUser(user);

        return questionList.stream()
                .map(question -> questionResponseMapper.convert(question))
                .collect(Collectors.toList());
    }

}
