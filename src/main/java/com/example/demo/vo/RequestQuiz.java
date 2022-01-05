package com.example.demo.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class RequestQuiz {

    private Long quizNum;
    @NotNull(message = "카테고리를 선택하세요")
    private Long categoryNum; //숫자로 입력
    @NotNull(message = "유저 정보가 입력되지 않았습니다")
    private Long userId; // 사용자 정보를 로그인 정보로 받아 오는 방법? or 직접 입력?
    @NotNull(message = "퀴즈 점수를 입력하세요")
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
