package com.example.demo.entity;

import lombok.Data;
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
    @ManyToOne
    @JoinColumn(name = "category_num")
    private CategoryEntity categoryEntity;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Setter
    @Column(name = "quiz_score")
    private Integer quizScore;

    @Setter
    @Column(name = "quiz_contents")
    private String quizContents;

    @Setter
    @Column(name = "quiz_answer")
    private String quizAnswer;

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


    //생성자 필요하면 따로 생성

}
