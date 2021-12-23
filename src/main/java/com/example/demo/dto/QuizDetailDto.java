package com.example.demo.dto;

import com.example.demo.entity.QuizEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@NoArgsConstructor
public class QuizDetailDto {
    private Long quizNum;
    private QuizEntity quizEntity;
    private Integer answerRate;
    private Integer trialUserCount;
    private Integer answerUserCount;


    public QuizDetailDto(QuizEntity source){
        copyProperties(source, this);
    }
}
