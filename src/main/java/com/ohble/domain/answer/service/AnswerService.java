package com.ohble.domain.answer.service;

import com.ohble.domain.answer.Answer;
import com.ohble.domain.answer.repository.AnswerRepository;
import com.ohble.domain.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    @Transactional
    public void createAnswer(String content, Long questionId) {
        Answer newAnswer = new Answer(content, questionService.loadQuestionById(questionId));
        answerRepository.save(newAnswer);
    }
}
