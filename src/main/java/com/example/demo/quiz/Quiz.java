package com.example.demo.quiz;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "QUIZ_LIST")
@Getter
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_num")
    private Integer quizNum;

    @Setter
    @Column(name = "category_num")
    private Integer categoryNum;

    @Setter
    @Column(name = "user_id")
    private String userId;

    @Setter
    @Column(name = "quiz_score")
    private Integer quizScore;

    @Setter
    @Column(name = "answer_rate")
    private Integer answerRate;

    @Setter
    @Column(name = "trial_user_count")
    private Integer trialUserCount;

    @Setter
    @Column(name = "answer_user_count")
    private Integer answerUserCount;

    //생성자 필요하면 따로 생성

}
