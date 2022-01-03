package com.example.demo.service;

import com.example.demo.dto.ResultOfUserSolutionDto;
import com.example.demo.dto.SubmittedUserSolutionDto;
import com.example.demo.entity.QuizEntity;
import com.example.demo.entity.UserQuizHistoryEntity;
import com.example.demo.jpa.QuizRepository;
import com.example.demo.jpa.UserQuizHistoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserQuizHistoryService {

    private final UserQuizHistoryRepository userQuizHistoryRepository;
    private final QuizRepository quizRepository;

    public UserQuizHistoryService(UserQuizHistoryRepository userQuizHistoryRepository, QuizRepository quizRepository){
        this.userQuizHistoryRepository = userQuizHistoryRepository;
        this.quizRepository = quizRepository;
    }
    @Transactional
    public ResultOfUserSolutionDto checkUserSolution(SubmittedUserSolutionDto submittedUserSolutionDto){
        if(submittedUserSolutionDto.isSolved()) return null;

        QuizEntity quizEntity = quizRepository.findById(submittedUserSolutionDto.getQuizNum())
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 퀴즈ID" + submittedUserSolutionDto.getQuizNum()));

        UserQuizHistoryEntity userQuizHistoryEntity = userQuizHistoryRepository.findByQuizEntityAndUserEntity(
                submittedUserSolutionDto.getQuizNum(),
                submittedUserSolutionDto.getUserId()
        ).orElseThrow(()-> new IllegalArgumentException("사용자ID가 올바르지 않습니다."));

        userQuizHistoryEntity.setTrialCount(userQuizHistoryEntity.getTrialCount()+1);
        userQuizHistoryEntity.setSolveTime(new Date());

        if(submittedUserSolutionDto.getAnswer().equals(quizEntity.getQuizAnswer())){
            float score = 10; // 점수 계산하는 메소드 구현해주세요! 그리고 userQuizHistoryEntity에 반영해주세요!
            userQuizHistoryEntity.setSolved(true);
            userQuizHistoryEntity.setSolveScore(score);
            userQuizHistoryRepository.save(userQuizHistoryEntity);
            return ResultOfUserSolutionDto.builder()
                    .userId(userQuizHistoryEntity.getUserEntity().getUserId())
                    .quizNum(userQuizHistoryEntity.getQuizEntity().getQuizNum())
                    .isSolved(userQuizHistoryEntity.isSolved())
                    .solveTime(new Date())
                    .solveScore(score)
                    .build();
        }

        userQuizHistoryRepository.save(userQuizHistoryEntity);
        return ResultOfUserSolutionDto.builder()
                .userId(userQuizHistoryEntity.getUserEntity().getUserId())
                .quizNum(userQuizHistoryEntity.getQuizEntity().getQuizNum())
                .solveScore(0.0f)
                .solveTime(new Date())
                .isSolved(userQuizHistoryEntity.isSolved())
                .build();
    }
}
