package com.ohble.domain.answer.repository;

import com.ohble.domain.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
