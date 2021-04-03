package com.project.forumapi.service.question;

import com.project.forumapi.exception.InvalidException;
import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.QuestionStatus;
import com.project.forumapi.model.User;
import com.project.forumapi.repository.QuestionRepository;
import com.project.forumapi.service.user.LoggedUserService;
import com.project.forumapi.validator.question.QuestionPertainToUserValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class FinishQuestionServiceTest {

    @InjectMocks
    FinishQuestionService finishQuestionService;

    @Mock
    QuestionRepository questionRepository;

    @Mock
    LoggedUserService loggedUserService;

    @Mock
    QuestionPertainToUserValidator questionPertainToUserValidator;

    @Test
    public void shouldFinishQuestionWithSuccess() {
        Long id = 1L;
        User user = new User();

        Question question = new Question();
        question.setQuestionStatus(QuestionStatus.UNSOLVED);

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(questionRepository.findById(id)).thenReturn(Optional.of(question));

        finishQuestionService.finish(id);

        Assert.assertEquals(QuestionStatus.SOLVED.name(), question.getQuestionStatus().name());
        Mockito.verify(questionRepository, Mockito.times(1)).findById(id);
        Mockito.verify(questionPertainToUserValidator, Mockito.times(1)).accept(user, question);
        Mockito.verify(questionRepository, Mockito.times(1)).save(question);
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailWhenQuestionNotFound() {
        Long id = 1L;
        User user = new User();

        Question question = new Question();

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(questionRepository.findById(id)).thenReturn(Optional.empty());

        try {
            finishQuestionService.finish(id);
        } catch (NotFoundException notFoundException) {
            Mockito.verify(questionRepository, Mockito.times(1)).findById(id);
            Mockito.verify(questionPertainToUserValidator, Mockito.times(0)).accept(user, question);
            Mockito.verify(questionRepository, Mockito.times(0)).save(question);

            throw notFoundException;
        }
    }

    @Test(expected = InvalidException.class)
    public void shouldFailWhenTheQuestionIsAlreadySolved() {
        Long id = 1L;
        User user = new User();

        Question question = new Question();
        question.setQuestionStatus(QuestionStatus.SOLVED);

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(questionRepository.findById(id)).thenReturn(Optional.of(question));

        try {
            finishQuestionService.finish(id);
        } catch(InvalidException invalidException) {
            Mockito.verify(questionRepository, Mockito.times(1)).findById(id);
            Mockito.verify(questionPertainToUserValidator, Mockito.times(1)).accept(user, question);
            Mockito.verify(questionRepository, Mockito.times(0)).save(question);

            throw invalidException;
        }
    }

}
