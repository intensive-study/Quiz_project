package com.example.demo.dto;

import com.example.demo.entity.QuizEntity;
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
    private String quizContents;
    private String quizAnswer;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;

    public QuizDto(QuizEntity source){
        copyProperties(source, this);
    }
}
