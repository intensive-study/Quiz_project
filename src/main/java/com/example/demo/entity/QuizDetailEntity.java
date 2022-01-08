package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "QUIZ_DETAIL")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert //insert시 값이 null이면 제외한다 -> default 설정값 넣어주기 위해 추가
public class QuizDetailEntity {

    @Id
    @Column(name = "quiz_num")
    private Long quizNum;

    @MapsId
    @OneToOne
    @JoinColumn(name = "quiz_num")
    private QuizEntity quizEntity;

    @Column(name = "answer_rate")
    private Float answerRate;

    @Column(name = "trial_user_count")
    private Integer trialUserCount;

    @Column(name = "answer_user_count")
    private Integer answerUserCount;

}
