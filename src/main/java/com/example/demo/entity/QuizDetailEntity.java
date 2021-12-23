package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "QUIZ_DETAIL")
@Data
@NoArgsConstructor
public class QuizDetailEntity {

    @Id
    @Column(name = "quiz_num")
    private Long quizNum; //  값을 Long 형태로 바꿨습니다.

    @OneToOne @MapsId
    @JoinColumn(name = "quiz_num")
    private QuizEntity quizEntity;

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
