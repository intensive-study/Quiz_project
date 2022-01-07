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

    /**
     * 카테고리 관련 서비스
     */
    List<CategoryEntity> getCategoryByAll();
    //생성
    CategoryEntity createQuizCategory(CategoryDto categoryDto) throws NameDuplicateException;
    //이름 변경
    @Transactional
    CategoryEntity updateCategory(CategoryDto categoryDto) throws IdNotExistException;
    //삭제
    @Transactional
    void deleteCategory(Long CategoryNum) throws IdNotExistException;

    /**
     * 퀴즈 관련 서비스
     */
    //조회
    List<QuizEntity> getQuizByAll();
    //개별 조회
    QuizEntity getQuizByQuizNum(Long quizNum) throws IdNotExistException;
    //카테고리별 조회
    List<QuizEntity> getQuizByCategoryNum(Long CategoryNum) throws IdNotExistException;
    //생성
    QuizEntity createQuiz(RequestQuiz quizDto) throws IdNotExistException;
    //변경
    @Transactional
    QuizEntity updateQuiz(RequestQuiz quizDto) throws IdNotExistException;
    //삭제
    @Transactional
    void deleteQuiz(Long quizNum, Long userId) throws IdNotExistException;

    /**
     * 퀴즈 디테일
     */
    //카운트
    @Transactional
    QuizDetailEntity updateQuizDetailByQuizNum(Long quizNum, boolean isSolved);


    //필요없음
//    QuizDetailEntity createQuizDetail(QuizDetailDto quizDetailDto);
    //필요없음(테스트용)
    List<QuizDetailEntity> getQuizDetailByAll();


}
