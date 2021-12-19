package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "QUIZ_LIST")
@Getter
@NoArgsConstructor
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_num")
    private Integer quizNum;

    @Setter
    @Column(name = "category_num")
    private Integer categoryNum;

    @Setter
    @Column(name = "user_id")
    private String userId;

    @Setter
    @Column(name = "quiz_score")
    private Integer quizScore;

    @Setter
    @Column(name = "quiz_contents")
    private String quizContents;

    @Setter
    private String choice1;
    @Setter
    private String choice2;
    @Setter
    private String choice3;
    @Setter
    private String choice4;
    @Setter
    private String choice5;

    @Setter
    @Column(name = "quiz_answer")
    private String quizAnswer;

    //생성자 필요하면 따로 생성

}
