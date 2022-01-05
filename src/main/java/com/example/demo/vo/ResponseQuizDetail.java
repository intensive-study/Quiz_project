package com.example.demo.vo;

import com.example.demo.entity.QuizEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseQuizDetail {
    private Long quizNum;
    private Integer answerRate;
    private Integer trialUserCount;
    private Integer answerUserCount;

}
