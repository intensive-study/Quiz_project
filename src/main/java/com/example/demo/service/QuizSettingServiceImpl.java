package com.example.demo.service;

import com.example.demo.dto.QuizDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.CategoryRepository;
import com.example.demo.jpa.QuizRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class QuizSettingServiceImpl implements QuizService {

    QuizRepository quizRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public QuizSettingServiceImpl(QuizRepository quizRepository, CategoryRepository categoryRepository){
        this.quizRepository = quizRepository;
        this.categoryRepository =categoryRepository;
    }

    @Override
    public QuizEntity createQuiz(QuizDto quizDto){

//        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(1);
//        quizDto.setCategoryNum(categoryEntity.get());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuizEntity quiz = mapper.map(quizDto, QuizEntity.class);

        quizRepository.save(quiz);
        return quiz;
    }

    @Override
    public void deleteQuiz(Integer quizNum) {
        quizRepository.deleteById(quizNum);
    }

    @Override
    public List<QuizEntity> getQuizByAll() {
        return quizRepository.findAll();
    }
}
