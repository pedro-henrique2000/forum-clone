package com.project.forumapi.service.question;

import com.project.forumapi.exception.NotFoundException;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.repository.QuestionRepository;
import com.project.forumapi.service.user.LoggedUserService;
import com.project.forumapi.validator.question.QuestionPertainToUserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DeleteQuestionServiceTest {

    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    DeleteQuestionService deleteQuestionService;

    @Mock
    QuestionPertainToUserValidator questionPertainToUserValidator;

    @Mock
    LoggedUserService loggedUserService;

    @Test
    public void shouldDeleteWithSuccess() {
        User user = new User();
        Question question = new Question();

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(questionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(question));

        deleteQuestionService.delete(1L);

        Mockito.verify(loggedUserService, Mockito.times(1)).getLoggedUser();
        Mockito.verify(questionRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(questionPertainToUserValidator, Mockito.times(1)).accept(user, question);
        Mockito.verify(questionRepository, Mockito.times(1)).delete(question);
    }

    @Test(expected = NotFoundException.class)
    public void shouldFailWhenQuestionNotFound() {
        User user = new User();
        Question question = new Question();

        Mockito.when(loggedUserService.getLoggedUser()).thenReturn(user);
        Mockito.when(questionRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        try {
            deleteQuestionService.delete(1L);
        } catch(NotFoundException notFoundException) {
            Mockito.verify(loggedUserService, Mockito.times(1)).getLoggedUser();
            Mockito.verify(questionRepository, Mockito.times(1)).findById(Mockito.any());
            Mockito.verify(questionPertainToUserValidator, Mockito.times(0)).accept(user, question);
            Mockito.verify(questionRepository, Mockito.times(0)).delete(Mockito.any());

            throw notFoundException;
        }
    }

}
