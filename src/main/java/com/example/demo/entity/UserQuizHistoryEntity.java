package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class UserQuizHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="quiz_num")
    private QuizEntity quizEntity;

    @Column(name="trial_count", nullable = false)
    private int trialCount;

    @Column(name="solve_time", nullable = false)
    private Date solveTime;

    @Column(name="solve_score", nullable = false)
    private float solveScore;

    @Column(name="is_solved", nullable = false)
    private boolean isSolved;

}
