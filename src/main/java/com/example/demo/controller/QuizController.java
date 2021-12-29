package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizEntity;
import com.example.demo.exception.NameDuplicateException;
import com.example.demo.service.QuizService;
import com.example.demo.vo.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping
    public List<QuizDto> getAllQuiz() {
        return this.quizService.getQuizByAll().stream()
                .map(QuizDto::new).collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<CategoryDto> getAllCategory() {
        return this.quizService.getCategoryByAll().stream()
                .map(CategoryDto::new).collect(Collectors.toList());
    }

    @PostMapping("/category/create")
    public ResponseEntity createCategory(@RequestBody CategoryDto categoryDto) throws NameDuplicateException {
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        CategoryDto categoryDto = mapper.map(category, CategoryDto.class);

        CategoryEntity responseCategory = quizService.createQuizCategory(categoryDto);
        System.out.println(responseCategory.getCategoryNum());

//        ResponseCategory responseCategory = mapper.map(responseCategoryEntity, ResponseCategory.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCategory);
    }

    @GetMapping("/{quizNum}")
    public ResponseEntity<ResponseQuiz> getQuiz(@PathVariable("quizNum") Long quizNum){

        QuizDto quizDto = quizService.getQuizByQuizNum(quizNum);
        ResponseQuiz responseQuiz = new ModelMapper().map(quizDto, ResponseQuiz.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseQuiz);
    }

    @PostMapping("/create")
    public ResponseEntity createQuiz(@RequestBody @Valid RequestQuiz quiz){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuizDto quizDto = mapper.map(quiz, QuizDto.class);
        QuizEntity responseQuizEntity = quizService.createQuiz(quizDto);

//        ResponseQuiz responseQuiz = mapper.map(responseQuizEntity, ResponseQuiz.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseQuizEntity);
    }
}
