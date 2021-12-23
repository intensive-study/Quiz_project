package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.service.QuizService;
import com.example.demo.vo.RequestUser;
import com.example.demo.vo.ResponseUser;
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
    public ResponseEntity createCategory(@RequestBody @Valid RequestUser category){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
        quizService.createQuizCategory(categoryDto);

        ResponseUser responseUser = mapper.map(categoryDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

    @GetMapping("/{quizNum}")
    public ResponseEntity<ResponseUser> getQuiz(@PathVariable("quizNum") Long quizNum){

        QuizDto quizDto = quizService.getQuizByQuizNum(quizNum);
        ResponseUser responseUser = new ModelMapper().map(quizDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    @PostMapping("/setting")
    public ResponseEntity createQuiz(@RequestBody @Valid RequestUser quiz){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuizDto quizDto = mapper.map(quiz, QuizDto.class);
        quizService.createQuiz(quizDto);

        ResponseUser responseUser = mapper.map(quizDto, ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
