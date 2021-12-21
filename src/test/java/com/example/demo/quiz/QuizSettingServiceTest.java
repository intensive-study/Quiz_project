package com.example.demo.quiz;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizEntity;
import com.example.demo.jpa.QuizRepository;
import com.example.demo.service.QuizService;
import com.example.demo.service.QuizSettingServiceImpl;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class QuizSettingServiceTest {

    private final QuizService quizService;

    @Autowired
    public QuizSettingServiceTest(QuizSettingServiceImpl quizService) {
        this.quizService = quizService;
    }

    @Test
    void 문제출제확인(){
        QuizDto quizDto = new QuizDto();
//        CategoryEntity category = new CategoryEntity();
//        category.setCategoryName("aaa");
//        quizDto.setCategoryNum(category);
//        quizDto.setUserId("user1");
        quizDto.setQuizScore(5);
        quizDto.setQuizContents("문제내용");
        quizDto.setQuizAnswer("choice3");
        quizDto.setChoice1("보기 내용");
        quizDto.setChoice2("보기 내용");
        quizDto.setChoice3("보기 내용");

        quizService.createQuiz(quizDto);
        QuizEntity result = quizService.createQuiz(quizDto);

        //테ce.createUser(userDto);
        List<QuizEntity> quizEntities = quizService.getQuizByAll();

        assertThat(quizEntities.size()).isEqualTo(result.getQuizNum());
    }

}