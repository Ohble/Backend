package com.ohble.domain.answer;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Answer(String content) {
        this.content = content;
    }
}
