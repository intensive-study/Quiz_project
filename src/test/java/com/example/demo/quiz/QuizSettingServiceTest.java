package com.example.demo.quiz;

import com.example.demo.dto.QuizDto;
import com.example.demo.entity.QuizEntity;
import com.example.demo.jpa.QuizRepository;
import com.example.demo.service.QuizService;
import com.example.demo.service.QuizSettingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;

class QuizSettingServiceTest {

    private QuizRepository quizRepository;
    private QuizService quizService;

    public QuizSettingServiceTest(QuizRepository quizRepository, QuizService quizService){
        this.quizRepository = quizRepository;
        this.quizService = quizService;
    }

    @Test
    void 문제출제확인(){
        QuizEntity quiz = new QuizEntity();
        quiz.setCategoryNum(1);
        quiz.setUserId("user1");
        quiz.setQuizScore(5);
        quiz.setQuizAnswer("choice3");
        quiz.setChoice1("보기 내용");
        quiz.setChoice2("보기 내용");
        quiz.setChoice3("보기 내용");

        QuizDto quizDto = new QuizDto(quiz);

        QuizEntity result = quizService.createQuiz(quizDto);

        assertThat(result.getChoice1()).isEqualTo(quiz.getChoice1());
    }

}