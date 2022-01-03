package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "QUIZ_LIST")
@Getter @Setter
@DynamicUpdate //choice5: 값이 있는 경우, Null 값으로 변경 어려움
@NoArgsConstructor
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_num")
    private Long quizNum;

    @OneToOne(mappedBy = "quizEntity", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private QuizDetailEntity quizDetailEntity;

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

    //객관식 보기 json 형식으로 저장으로 바꿔서 속성 한 개로 줄이고 싶음
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;

    //생성자 필요하면 따로 생성

}
