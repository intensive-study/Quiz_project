package com.example.demo.dto;

import com.example.demo.entity.QuizEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
public class QuizDetailDto {
    private Integer quizNum;
    private QuizEntity quizEntity;
    private Integer answerRate;
    private Integer trialUserCount;
    private Integer answerUserCount;


    public QuizDetailDto(QuizEntity source){
        copyProperties(source, this);
    }
}
