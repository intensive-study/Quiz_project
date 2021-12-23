package com.example.demo.dto;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizDetailEntity;
import com.example.demo.entity.QuizEntity;
import com.example.demo.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@NoArgsConstructor
public class QuizDto {
    private Long quizNum;
    private QuizDetailEntity quizDetailEntity;
    private CategoryEntity categoryEntity;
    private UserEntity userEntity;
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
