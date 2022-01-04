package com.example.demo.vo;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseQuiz {
    private Long quizNum;
    private ResponseQuizDetail quizDetail;
    private CategoryEntity categoryEntity;
    private Long userId;
    private Integer quizScore;
    private String quizContents;
    private String quizAnswer;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;

    public ResponseQuiz(QuizEntity quizEntity){
        this.quizNum = quizEntity.getQuizNum();
        this.quizDetail = ResponseQuizDetail.builder().quizNum(quizEntity.getQuizDetailEntity().getQuizNum())
                .answerRate(quizEntity.getQuizDetailEntity().getAnswerRate())
                .answerUserCount(quizEntity.getQuizDetailEntity().getAnswerUserCount())
                .trialUserCount(quizEntity.getQuizDetailEntity().getTrialUserCount()).build();
        this.categoryEntity = quizEntity.getCategoryEntity();
        this.userId = quizEntity.getUserEntity().getUserId();
        this.quizScore = quizEntity.getQuizScore();
        this.quizContents = quizEntity.getQuizContents();
        this.quizAnswer = quizEntity.getQuizAnswer();
        this.choice1 = quizEntity.getChoice1();
        this.choice2 = quizEntity.getChoice2();
        this.choice3 = quizEntity.getChoice3();
        this.choice4 = quizEntity.getChoice4();
        this.choice5 = quizEntity.getChoice5();
    }

}
