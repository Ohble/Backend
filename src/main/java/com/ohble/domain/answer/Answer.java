package com.ohble.domain.answer;

import com.ohble.domain.question.Question;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String content1;

    @Column(nullable = false, length = 255)
    private String content2;

    @Column(nullable = false, length = 255)
    private String content3;

    @Column(nullable = false, length = 255)
    private String content4;

    @Column(nullable = false, length = 255)
    private String content5;

    @Column(nullable = false, length = 255)
    private String content6;

    @Column(nullable = false, length = 255)
    private String content7;

    @Column(nullable = false, length = 255)
    private String content8;

    @Column(nullable = false, length = 255)
    private String content9;

    @Column(nullable = false, length = 255)
    private String content10;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @JoinColumn(columnDefinition = "question_id")
    @ManyToOne
    private Question question;
}
