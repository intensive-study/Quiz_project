package com.example.demo.dto;

import com.example.demo.entity.QuizEntity;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
public class QuizDetailDTO {
    private Integer quizNum;
    private Integer answerRate;
    private Integer trialUserCount;
    private Integer answerUserCount;


    public QuizDetailDTO(QuizEntity source){
        copyProperties(source, this);
    }
}
