package com.example.demo.quiz;

import lombok.Getter;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
public class QuizDto {
    private Integer quizNum;
    private Integer categoryNum;
    private String userId;
    private Integer quizScore;
    private Integer answerRate;
    private Integer trialUserCount;
    private Integer answerUserCount;

    public QuizDto(Quiz source){
        copyProperties(source, this);
    }
}
