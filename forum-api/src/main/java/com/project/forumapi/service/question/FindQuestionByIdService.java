package com.project.forumapi.service.question;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.mapper.question.QuestionResponseMapper;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.response.QuestionResponse;
import com.project.forumapi.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindQuestionByIdService {

    @Autowired
    QuestionRepository repository;

    @Autowired
    QuestionResponseMapper questionResponseMapper;

    public QuestionResponse findById(Long id) {
        Question question = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found a question with id %d", id)));

        return questionResponseMapper.convert(question);
    }

}
