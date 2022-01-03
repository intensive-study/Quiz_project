package com.example.demo.jpa;

import com.example.demo.entity.QuizEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserQuizHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserQuizHistoryRepository extends JpaRepository<UserQuizHistoryEntity, Long> {

    //Optional<UserQuizHistoryEntity> findByQuizNumAndUserId(Long quizNum, Long userId);

    @Query(value = "select * from user_quiz_history h where  h.quiz_num = ?#{#quizEntity.quiz_num} and h.user_id = ?#{#userEntity.user_id}", nativeQuery = true)
    Optional<UserQuizHistoryEntity> findByQuizNumAndUserId(@Param("quizEntity")QuizEntity quizEntity, @Param("userEntity")UserEntity userEntity);

}
