package com.ohble.domain.question.service;

import com.ohble.domain.question.Question;
import com.ohble.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public void createQuestion(String content) {
        Question newQuestion = new Question(content);
        questionRepository.save(newQuestion);
    }

    public Question loadQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Question> loadAllQuestion() {
        return questionRepository.findAll();
    }
}
