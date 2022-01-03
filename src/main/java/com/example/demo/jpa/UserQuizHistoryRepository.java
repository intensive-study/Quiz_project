package com.example.demo.jpa;

import com.example.demo.entity.UserQuizHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserQuizHistoryRepository extends JpaRepository<UserQuizHistoryEntity, Long> {

    Optional<UserQuizHistoryEntity> findByQuizEntityAndUserEntity(Long quizNum, Long userId);

}
