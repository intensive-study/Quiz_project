package com.example.demo.service;

import com.example.demo.dto.UserAnswerDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.UserQuizHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserQuizHistoryService {

    private final UserQuizHistoryRepository userQuizHistoryRepository;

    @Transactional
    public Integer update(UserAnswerDto userAnswerDto){
        List<UserEntity> userEntitis = userQuizHistoryRepository.findByUserId(userAnswerDto.getUserId());

        return 1;
    }
}
