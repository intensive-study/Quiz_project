package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "QUIZ_DETAIL")
@Getter
@NoArgsConstructor
public class QuizDetailEntity {
    @Id
    @Column(name = "quiz_num")
    private Integer quizNum;

    @Setter
    @Column(name = "answer_rate")
    private Integer answerRate;

    @Setter
    @Column(name = "trial_user_count")
    private Integer trialUserCount;

    @Setter
    @Column(name = "answer_user_count")
    private Integer answerUserCount;


}
