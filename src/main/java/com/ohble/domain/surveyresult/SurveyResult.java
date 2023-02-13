package com.ohble.domain.surveyresult;

import com.ohble.domain.participant.Participant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @Column
    private String question1;

    @Column
    private String answer1;

    @Column
    private String question2;

    @Column
    private String answer2;

    @Column
    private String question3;

    @Column
    private String answer3;

    @Column
    private String question4;

    @Column
    private String answer4;

    @Column
    private String question5;

    @Column
    private String answer5;

    @Column
    private String question6;

    @Column
    private String answer6;

    @Column
    private String question7;

    @Column
    private String answer7;

    @Column
    private String question8;

    @Column
    private String answer8;

    @Column
    private String question9;

    @Column
    private String answer9;

    @Column
    private String question10;

    @Column
    private String answer10;

    @Column
    private String question11;

    @Column
    private String answer11;

    @Column
    private String question12;

    @Column
    private String answer12;

    @Column
    private String question13;

    @Column
    private String answer13;

    @Column
    private String question14;

    @Column
    private String answer14;

    public SurveyResult setSurveyResult(Participant participant, List<String> questions, List<String> answers) {
        this.participant = participant;

        this.question1 = questions.get(0);
        this.question2 = questions.get(1);
        this.question3 = questions.get(2);
        this.question4 = questions.get(3);
        this.question5 = questions.get(4);
        this.question6 = questions.get(5);
        this.question7 = questions.get(6);
        this.question8 = questions.get(7);
        this.question9 = questions.get(8);
        this.question10 = questions.get(9);
        this.question11 = questions.get(10);
        this.question12 = questions.get(11);
        this.question13 = questions.get(12);
        this.question14 = questions.get(13);

        this.answer1 = answers.get(0);
        this.answer2 = answers.get(1);
        this.answer3 = answers.get(2);
        this.answer4 = answers.get(3);
        this.answer5 = answers.get(4);
        this.answer6 = answers.get(5);
        this.answer7 = answers.get(6);
        this.answer8 = answers.get(7);
        this.answer9 = answers.get(8);
        this.answer10 = answers.get(9);
        this.answer11 = answers.get(10);
        this.answer12 = answers.get(11);
        this.answer13 = answers.get(12);
        this.answer14 = answers.get(13);

        return this;
    }
}
