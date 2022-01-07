package com.example.demo.jpa;

import com.example.demo.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    //카테고리별 문제 조회
    @Query(value = "select * from quiz_list q where q.category_num = :categoryNum", nativeQuery = true)
    Collection<QuizEntity> findByCategoryNum(Long categoryNum);

    @Query(value = "select * from quiz_list q where q.category_num is NULL", nativeQuery = true)
    Collection<QuizEntity> findByCategoryNull();

    @Modifying
    @Query(value = "UPDATE quiz_list q set q.category_num = NULL where q.category_num = :categoryNum", nativeQuery = true)
    void updateCategoryNull(Long categoryNum);

}
