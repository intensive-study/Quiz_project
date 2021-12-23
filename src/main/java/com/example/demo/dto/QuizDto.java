package com.example.demo.dto;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizEntity;
import com.example.demo.entity.UserEntity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
@NoArgsConstructor
public class QuizDto {
    private Integer quizNum;
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
