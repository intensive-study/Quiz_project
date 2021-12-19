package com.example.demo.service;

import com.example.demo.dto.QuizDto;
import com.example.demo.entity.QuizEntity;
import com.example.demo.jpa.QuizRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuizSettingServiceImpl implements QuizService {

    QuizRepository quizRepository;

    @Autowired
    public QuizSettingServiceImpl(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    @Override
    public QuizEntity createQuiz(QuizDto quizDto){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuizEntity quiz = mapper.map(quizDto, QuizEntity.class);

        quizRepository.save(quiz);
        System.out.println("quizRepository null X");

        return quiz;
    }

    @Override
    public void deleteQuiz(Integer quizNum) {
        quizRepository.deleteById(quizNum);
    }
}
