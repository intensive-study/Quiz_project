package com.example.demo.service;

import com.example.demo.dto.QuizDto;
import com.example.demo.entity.QuizEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuizService {
    QuizEntity createQuiz(QuizDto quizDto);

    @Transactional
    void deleteQuiz(Integer quizNum);
    List<QuizEntity> getQuizByAll();
}
