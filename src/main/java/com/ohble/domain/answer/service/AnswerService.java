package com.ohble.domain.answer.service;

import com.ohble.domain.answer.Answer;
import com.ohble.domain.answer.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Transactional
    public void createAnswer(String content) {
        Answer newAnswer = new Answer(content);
        answerRepository.save(newAnswer);
    }
}
