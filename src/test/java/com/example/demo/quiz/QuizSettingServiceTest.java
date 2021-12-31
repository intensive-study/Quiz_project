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


    void 사용자데이터추가(){

        UserDto userDto = new UserDto();

        userDto.setUserId("user2");
        userDto.setName("user_name");
        userDto.setPassword("!2342");

        userService.createUser(userDto);

    }

    @Test
    void 문제변경확인(){
        this.문제출제확인();

        QuizDto quizDto = new QuizDto();
        quizDto.setQuizNum(Long.valueOf(1));
        quizDto.setQuizContents("문제내용");
        quizDto.setQuizAnswer("choice1");
        quizService.updateQuiz(quizDto);

        List<QuizEntity> quizEntities = quizService.getQuizByAll();
        List<QuizDetailEntity> quizDetailEntities = quizService.getQuizDetailByAll();

        System.out.println(quizEntities.get(quizEntities.size()-1).getQuizAnswer() +" "+ quizDetailEntities.get(quizDetailEntities.size()-1).getQuizEntity().getQuizAnswer());
        System.out.println(quizEntities.size()+" "+quizDetailEntities.size());

    }

    @Test
    void 문제출제확인(){

//        CategoryEntity categoryEntity = this.카테고리추가();
        this.사용자데이터추가();
        Iterable<UserEntity> userEntity = userService.getUsersByAll();

        QuizDto quizDto = new QuizDto();
//        quizDto.setCategoryEntity(categoryEntity);
        quizDto.setUserEntity(userEntity.iterator().next());
        quizDto.setQuizContents("문제내용");
        quizDto.setQuizAnswer("choice3");
        quizDto.setChoice3("보기 내용");

        QuizEntity result = quizService.createQuiz(quizDto);

        //들어간 값 확인
        List<QuizEntity> quizEntities = quizService.getQuizByAll();
        List<QuizDetailEntity> quizDetailEntities = quizService.getQuizDetailByAll();

        System.out.println(quizEntities.get(quizEntities.size()-1).getQuizAnswer() +" "+ quizDetailEntities.get(quizDetailEntities.size()-1).getTrialUserCount());
        System.out.println(quizEntities.size()+" "+quizDetailEntities.size());


        //방금 넣어준 데이터와 테이블에 마지막으로 들어간 데이터가 같은지 확인
        assertThat(quizEntities.get(quizEntities.size()-1).getQuizNum()).isEqualTo(result.getQuizNum());
    }

    @Test
    void 디테일변경확인(){

        this.문제출제확인();

        quizService.updateQuizDetailByQuizNum(Long.valueOf(1));

        List<QuizEntity> quizEntities = quizService.getQuizByAll();
        List<QuizDetailEntity> quizDetailEntities = quizService.getQuizDetailByAll();

        System.out.println(quizEntities.get(quizEntities.size()-1).getQuizNum() +" "+ quizDetailEntities.get(quizDetailEntities.size()-1).getAnswerRate());
        System.out.println(quizEntities.size()+" "+quizDetailEntities.size());

        //방금 넣어준 데이터와 테이블에 마지막으로 들어간 데이터가 같은지 확인
        assertThat(quizEntities.size()).isEqualTo(quizDetailEntities.size());
    }

    @Test
    void 퀴즈삭제확인(){
        this.문제출제확인();
        QuizDto Q = quizService.getQuizByQuizNum( Long.valueOf(1));
        System.out.println(Q.getQuizNum()+" "+Q.getQuizAnswer());

        quizService.deleteQuiz( Long.valueOf(1));

        List<QuizEntity> quizEntities = quizService.getQuizByAll();
        List<QuizDetailEntity> quizDetailEntities = quizService.getQuizDetailByAll();

        System.out.println(quizEntities.size()+" "+quizDetailEntities.size());
        assertThat(quizEntities.size()).isEqualTo(quizDetailEntities.size());
    }

}