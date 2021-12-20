package com.example.demo.controller;

import com.example.demo.dto.UserAnswerDto;
import com.example.demo.service.UserQuizHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.QuizService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final UserQuizHistoryService userQuizHistoryService;

    @PostMapping("/sub/ans")
    public Integer submitUserAnswer(@RequestBody UserAnswerDto userAnswerDto){
        return userQuizHistoryService.update(userAnswerDto);
    }
}
