package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestQuiz {

    private Long quizNum;
    private Long categoryNum; //숫자로 입력
    private Long userId; // 사용자 정보를 로그인 정보로 받아 오는 방법? or 직접 입력?
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
