package com.example.demo.quiz;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizDetailEntity;
import com.example.demo.entity.QuizEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.QuizService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor
class QuizSettingServiceTest {

    private final QuizService quizService;
    private final UserService userService;

  /*  @Autowired
    public QuizSettingServiceTest(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }*/

//    CategoryEntity 카테고리추가(){
//        CategoryDto categoryDto = new CategoryDto();
//        categoryDto.setCategoryName("운영체제");
//
//        CategoryEntity result = quizService.createQuizCategory(categoryDto);
//
//        List<CategoryEntity> categoryEntities = quizService.getCategoryByAll();
//
//        //방금 넣어준 데이터와 테이블에 마지막으로 들어간 데이터가 같은지 확인
//        assertThat(categoryEntities.get(categoryEntities.size()-1).getCategoryNum()).isEqualTo(result.getCategoryNum());
//        return categoryEntities.get(categoryEntities.size()-1);
//    }

}