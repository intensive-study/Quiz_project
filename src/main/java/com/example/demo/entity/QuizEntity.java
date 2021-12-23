package com.example.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "QUIZ_LIST")
@NoArgsConstructor
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_num")
    private Long quizNum;

    @Column(name="category_num", insertable = false, updatable = false)
    private Integer categoryNum;

    @Column(name = "user_Id", insertable = false, updatable = false)
    private String userId;

    @ManyToOne
    @JoinColumn(name = "category_num")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "quiz_score")
    private Integer quizScore;

    @Column(name = "quiz_contents")
    private String quizContents;

    @Column(name = "quiz_answer")
    private String quizAnswer;

    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;


    //생성자 필요하면 따로 생성

}
