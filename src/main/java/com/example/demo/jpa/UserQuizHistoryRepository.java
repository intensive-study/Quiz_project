package com.example.demo.jpa;

import com.example.demo.entity.UserQuizHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuizHistoryRepository extends JpaRepository<UserQuizHistoryEntity, Long> {

}
