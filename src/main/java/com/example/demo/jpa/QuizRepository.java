package com.example.demo.jpa;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    @Query(value = "select * from quiz_list q where q.category_num = ?#{#categoryNum}", nativeQuery = true)
    Optional<QuizEntity> findByCategoryNum(@Param("categoryNum") Long categoryNum);
}
