package com.example.demo.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "user_quiz_history")
@NoArgsConstructor
public class UserQuizHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public UserQuizHistoryEntity(Long id, UserEntity userEntity, QuizEntity quizEntity, int trialCount, float solveScore, boolean isSolved){
        this.id = id;
        this.userEntity = userEntity;
        this.quizEntity = quizEntity;
        this.trialCount = trialCount;
        this.solveScore = solveScore;
        this.isSolved = isSolved;
    }

}
