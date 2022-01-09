package com.example.demo.jpa;

import com.example.demo.entity.QuizDetailEntity;
import com.example.demo.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDetailRepository extends JpaRepository<QuizDetailEntity, Long> {
}
