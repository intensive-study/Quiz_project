package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class SubmittedUserSolutionDto {

    private String userId;
    private Long quizNum;
    private String answer;
    private boolean solved;

    public SubmittedUserSolutionDto(String userId, Long quizNum, String answer, boolean solved) {
        this.userId = userId;
        this.quizNum = quizNum;
        this.answer = answer;
        this.solved = solved;
    }
}