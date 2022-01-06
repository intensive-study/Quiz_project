package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizDetailEntity;
import com.example.demo.entity.QuizEntity;
import com.example.demo.exception.IdNotExistException;
import com.example.demo.exception.NameDuplicateException;
import com.example.demo.vo.RequestQuiz;
import com.example.demo.vo.ResponseQuiz;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuizService {

    //카테고리 생성
    CategoryEntity createQuizCategory(CategoryDto categoryDto) throws NameDuplicateException;
    //카테고리 전체 보기
    List<CategoryEntity> getCategoryByAll();
    //카테고리 이름변경
    @Transactional
    CategoryEntity updateCategory(CategoryDto categoryDto);

    //퀴즈 관련
    QuizEntity createQuiz(RequestQuiz quizDto) throws IdNotExistException;
    List<QuizEntity> getQuizByAll();
    QuizEntity getQuizByQuizNum(Long quizNum) throws IdNotExistException;
    
    @Transactional
    void deleteQuiz(Long quizNum);
    @Transactional
    QuizEntity updateQuiz(RequestQuiz quizDto) throws IdNotExistException;
    @Transactional
    QuizDetailEntity updateQuizDetailByQuizNum(Long quizNum, boolean isSolved);


    //필요없음
//    QuizDetailEntity createQuizDetail(QuizDetailDto quizDetailDto);
    //필요없음(테스트용)
    List<QuizDetailEntity> getQuizDetailByAll();


}
