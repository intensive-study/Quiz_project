package com.example.demo.vo;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizDetailEntity;
import com.example.demo.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestQuiz {
    private Long quizNum;
//    private QuizDetailEntity quizDetailEntity;
    private CategoryEntity categoryEntity; //숫자로 입력
//    private UserEntity userEntity; // 사용자 정보를 로그인 상태에서 받아 오는 방법? or 직접 입력
    private Integer quizScore;

    @NotNull(message = "문제 질문을 입력하세요")
    private String quizContents;

    @NotNull(message = "정답을 입력하세요")
    private String quizAnswer;

    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;
}
