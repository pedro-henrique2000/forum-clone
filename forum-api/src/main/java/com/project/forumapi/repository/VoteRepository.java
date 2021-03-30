package com.project.forumapi.repository;

import com.project.forumapi.model.Answer;
import com.project.forumapi.model.Question;
import com.project.forumapi.model.User;
import com.project.forumapi.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByAnswer(Answer answer);
    Optional<Vote> findByAnswerAndUser(Answer answer, User user);
}
