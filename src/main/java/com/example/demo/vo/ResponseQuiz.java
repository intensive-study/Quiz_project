package com.example.demo.vo;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizDetailEntity;
import com.example.demo.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseQuiz {
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

}
