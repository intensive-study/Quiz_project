package com.example.demo.service;

import com.example.demo.dto.ResultOfUserSolutionDto;
import com.example.demo.dto.SubmittedUserSolutionDto;
import com.example.demo.entity.QuizEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserQuizHistoryEntity;
import com.example.demo.jpa.QuizRepository;
import com.example.demo.jpa.UserQuizHistoryRepository;

import com.example.demo.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserQuizHistoryService {

    private final UserQuizHistoryRepository userQuizHistoryRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public UserQuizHistoryService(UserQuizHistoryRepository userQuizHistoryRepository, QuizRepository quizRepository, UserRepository userRepository){
        this.userQuizHistoryRepository = userQuizHistoryRepository;
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResultOfUserSolutionDto checkUserSolution(SubmittedUserSolutionDto submittedUserSolutionDto) {
        QuizEntity quizEntity = quizRepository.findById(submittedUserSolutionDto.getQuizNum())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀴즈ID" + submittedUserSolutionDto.getQuizNum()));

        UserEntity userEntity = userRepository.findById(submittedUserSolutionDto.getUserId()).orElseThrow(
                () ->new IllegalArgumentException("존재하지 않는 사용자ID" + submittedUserSolutionDto.getUserId())
        );

        UserQuizHistoryEntity userQuizHistoryEntity = userQuizHistoryRepository.findByQuizNumAndUserId(
                quizEntity,
                userEntity
        ).orElse(new UserQuizHistoryEntity(0L, userEntity, quizEntity, 0, 0.0f, false));

        if(userQuizHistoryEntity.isSolved()) return null;

        userQuizHistoryEntity.setTrialCount(userQuizHistoryEntity.getTrialCount() + 1);
        userQuizHistoryEntity.setSolveTime(new Date());

        if (submittedUserSolutionDto.getAnswer().equals(quizEntity.getQuizAnswer())) {
            float score = (float)quizEntity.getQuizScore() / userQuizHistoryEntity.getTrialCount();
            score = Float.parseFloat(String.format("%.3f", score));
            userQuizHistoryEntity.setSolved(true);
            userQuizHistoryEntity.setSolveScore(score);
            userQuizHistoryRepository.save(userQuizHistoryEntity);
            userEntity.setTotalScore(userEntity.getTotalScore() + score);
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
