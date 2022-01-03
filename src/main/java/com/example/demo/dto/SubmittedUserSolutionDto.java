package com.example.demo.dto;

import com.example.demo.entity.UserQuizHistoryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class SubmittedUserSolutionDto {

    private Long userId;
    private Long quizNum;
    private String answer;
    private boolean solved;

    public SubmittedUserSolutionDto(Long userId, Long quizNum, String answer, boolean solved) {
        this.userId = userId;
        this.quizNum = quizNum;
        this.answer = answer;
        this.solved = solved;
    }

}
