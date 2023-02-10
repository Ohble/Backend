package com.ohble.domain.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String questionContent;

    @Column
    private String answerContent1;

    @Column
    private String answerContent2;

    @Column
    private String answerContent3;

    @Column
    private String answerContent4;

    @Column
    private String answerContent5;

    @Column
    private String answerContent6;

    @Column
    private String answerContent7;

    @Column
    private String answerContent8;

    @Column
    private String answerContent9;

    @Column
    private String answerContent10;
}