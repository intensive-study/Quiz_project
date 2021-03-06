package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
public class ResultOfUserSolutionDto {

    private Long quizNum;
    private Long userId;
    private double solveScore;
    private Date solveTime;
    private boolean isSolved;

    @Builder
    public ResultOfUserSolutionDto(Long quizNum, Long userId, double solveScore, Date solveTime, boolean isSolved) {
        this.quizNum = quizNum;
        this.userId = userId;
        this.solveScore = solveScore;
        this.solveTime = solveTime;
        this.isSolved = isSolved;
    }
}
