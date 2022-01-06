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

    public SubmittedUserSolutionDto(Long userId, Long quizNum, String answer) {
        this.userId = userId;
        this.quizNum = quizNum;
        this.answer = answer;
    }

}
