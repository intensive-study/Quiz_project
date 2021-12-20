package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="USER_QUIZ_HISTORY")
public class UserQuizHistoryEntity {

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="quiz_num")
    private QuizEntity quizEntity;

    @Column(name = "trial_count",nullable = false)
    private int trialCount;

    @Column(name="is_solved" ,nullable = false)
    private boolean isSolved;

}
