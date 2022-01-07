package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.IdNotExistException;
import com.example.demo.exception.NameDuplicateException;
import com.example.demo.service.QuizService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final QuizService quizService;

    @Autowired
    public AdminController(UserService userService, QuizService quizService){
        this.userService = userService;
        this.quizService = quizService;
    }
    /*
    회원 정보 관리
     */
    // 전체 회원 정보 조회
    @GetMapping("/users")
    public ResponseEntity<Iterable<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(userService.getUsersByAll());
    }

    // {username} 회원 정보 조회
    @GetMapping("/users/{username}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
    }

    /*
    카테고리 관리
     */
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
    public ResponseEntity DeleteCategory(@PathVariable("categoryNum") Long categoryNum) throws IdNotExistException {
        quizService.deleteCategory(categoryNum);
        return ResponseEntity.status(HttpStatus.OK).body("category id : " + categoryNum + " 삭제 완료");
    }

}
