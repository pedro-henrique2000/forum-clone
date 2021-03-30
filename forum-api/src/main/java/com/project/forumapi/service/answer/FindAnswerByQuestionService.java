package com.project.forumapi.service.answer;

import com.project.forumapi.mapper.answer.AnswerResponseMapper;
import com.project.forumapi.model.Answer;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.response.AnswerResponse;
import com.project.forumapi.repository.AnswerRepository;
import com.project.forumapi.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAnswerByQuestionService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerResponseMapper answerResponseMapper;

    public List<AnswerResponse> find(Question question) {
        List<Answer> answerList = answerRepository.findByQuestion(question);
        return answerList.stream()
                .map(answer -> answerResponseMapper.convert(answer))
                .collect(Collectors.toList());
    }
}
