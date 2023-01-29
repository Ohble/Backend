package com.ohble.domain.answer;

import com.ohble.domain.question.Question;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JoinColumn(columnDefinition = "question_id")
    @ManyToOne
    private Question question;

    public Answer(String content, Question question) {
        this.content = content;
        this.question = question;
    }
}
