package com.example.demo.jpa;

import com.example.demo.entity.QuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends JpaRepository<QuizEntity, Integer> {

}
