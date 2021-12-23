package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizDetailEntity;
import com.example.demo.entity.QuizEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuizService {

    //카테고리 생성
    CategoryEntity createQuizCategory(CategoryDto categoryDto);
    //카테고리 전체 보기
    List<CategoryEntity> getCategoryByAll();
    //카테고리 이름변경
    @Transactional
    CategoryEntity updateCategory(CategoryDto categoryDto);

    //퀴즈 관련
    QuizEntity createQuiz(QuizDto quizDto);
    List<QuizEntity> getQuizByAll();
    QuizDto getQuizByQuizNum(Integer quizNum);
    
    @Transactional
    void deleteQuiz(Integer quizNum);
    @Transactional
    QuizEntity updateQuiz(QuizDto quizDto);
    @Transactional
    QuizDetailEntity updateQuizDetailByQuizNum(Integer quizNum);


    //필요없음
//    QuizDetailEntity createQuizDetail(QuizDetailDto quizDetailDto);
    //필요없음(테스트용)
    List<QuizDetailEntity> getQuizDetailByAll();


}
