package com.example.demo.dto;

import lombok.Builder;

import java.util.Date;

public class ResultOfUserSolutionDto {

    private Long quizNum;
    private String userId;
    private float solveScore;
    private Date solveTime;
    private boolean isSolved;

    @Builder
    public ResultOfUserSolutionDto(Long quizNum, String userId, float solveScore, Date solveTime, boolean isSolved) {
        this.quizNum = quizNum;
        this.userId = userId;
        this.solveScore = solveScore;
        this.solveTime = solveTime;
        this.isSolved = isSolved;
    }
}
