package com.project.forumapi.service.answer;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.mapper.answer.AnswerResponseMapper;
import com.project.forumapi.model.Answer;
import com.project.forumapi.model.response.AnswerResponse;
import com.project.forumapi.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindAnswerByIdService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    AnswerResponseMapper answerResponseMapper;

    public AnswerResponse findById(Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found answer with id %d", id)));

        return answerResponseMapper.convert(answer);
    }
}
