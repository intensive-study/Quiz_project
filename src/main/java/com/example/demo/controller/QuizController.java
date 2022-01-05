package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.dto.ResultOfUserSolutionDto;
import com.example.demo.dto.SubmittedUserSolutionDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizEntity;
import com.example.demo.exception.IdNotExistException;
import com.example.demo.exception.NameDuplicateException;
import com.example.demo.service.QuizService;
import com.example.demo.service.UserQuizHistoryService;
import com.example.demo.vo.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;
    private final UserQuizHistoryService userQuizHistoryService;

    @Autowired
    public QuizController(QuizService quizService, UserQuizHistoryService userQuizHistoryService){
        this.quizService = quizService;
        this.userQuizHistoryService = userQuizHistoryService;
    }

    @GetMapping
    public List<ResponseQuiz> getAllQuiz() {
        return this.quizService.getQuizByAll().stream()
                .map(ResponseQuiz::new).collect(Collectors.toList());
    }

    @GetMapping("/category")
    public List<CategoryDto> getAllCategory() {
        return this.quizService.getCategoryByAll().stream()
                .map(CategoryDto::new).collect(Collectors.toList());
    }

    @PostMapping("/category/create")
    public ResponseEntity createCategory(@RequestBody CategoryDto categoryDto) throws NameDuplicateException {

        CategoryEntity responseCategory = quizService.createQuizCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCategory);
    }

    @PutMapping("/category/update")
    public ResponseEntity UpdateCategory(@RequestBody CategoryDto categoryDto) throws IdNotExistException {

        CategoryEntity responseCategory = quizService.updateCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseCategory);
    }

    @DeleteMapping("/category/delete/{categoryNum}")
    @PreAuthorize("hasAnyRole('ADMIN')")  //관리자 권한만 접근
    public ResponseEntity DeleteCategory(@PathVariable("categoryNum") Long categoryNum) throws IdNotExistException {
        quizService.deleteCategory(categoryNum);
        return ResponseEntity.status(HttpStatus.OK).body("category id : " + categoryNum + " 삭제 완료");
    }

    @GetMapping("/{quizNum}")
    public ResponseEntity<ResponseQuiz> getQuiz(@PathVariable("quizNum") Long quizNum) throws IdNotExistException {

        QuizEntity quizEntity = quizService.getQuizByQuizNum(quizNum);
        ResponseQuiz responseQuiz = new ResponseQuiz(quizEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseQuiz);
    }

    @PostMapping("/create")
    public ResponseEntity createQuiz(@RequestBody @Valid RequestQuiz requestQuiz) throws IdNotExistException {
        QuizEntity quizEntity = quizService.createQuiz(requestQuiz);
        ResponseQuiz responseQuiz = new ResponseQuiz(quizEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseQuiz);
    }

    @PutMapping("/update")
    public ResponseEntity updateQuiz(@RequestBody @Valid RequestQuiz requestQuiz) throws IdNotExistException {
        //사용자 정보 변경 불가
        QuizEntity quizEntity = quizService.updateQuiz(requestQuiz);
        ResponseQuiz responseQuiz = new ResponseQuiz(quizEntity);
        System.out.println(requestQuiz.getQuizNum());

        return ResponseEntity.status(HttpStatus.OK).body(responseQuiz);
    }

    @DeleteMapping("/delete/{quizNum}")
    public ResponseEntity DeleteQuiz(@PathVariable("quizNum") Long quizNum, @RequestBody @Valid Long userId) throws IdNotExistException {
        // 임시로 userId 전달.. 사용자 id를 받아오는 다른 방법있으면 변경할 예정
        quizService.deleteQuiz(quizNum, userId);
        return ResponseEntity.status(HttpStatus.OK).body("quiz id : " + quizNum + " 삭제 완료");
    }

    @PostMapping("/user/solution")
    public ResponseEntity<ResultOfUserSolutionDto> checkUserSolution(@RequestBody @Valid SubmittedUserSolutionDto userSolutionDto){
        ResultOfUserSolutionDto resultOfUserSolutionDto = userQuizHistoryService.checkUserSolution(userSolutionDto);
        if(resultOfUserSolutionDto == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(resultOfUserSolutionDto);
    }
}
