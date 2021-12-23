package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizDetailEntity;
import com.example.demo.entity.QuizEntity;
import com.example.demo.jpa.CategoryRepository;
import com.example.demo.jpa.QuizDetailRepository;
import com.example.demo.jpa.QuizRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class QuizSettingServiceImpl implements QuizService {

    CategoryRepository categoryRepository;
    QuizRepository quizRepository;
    QuizDetailRepository quizDetailRepository;

    @Autowired
    public QuizSettingServiceImpl(CategoryRepository categoryRepository, QuizRepository quizRepository, QuizDetailRepository quizDetailRepository){
        this.categoryRepository = categoryRepository;
        this.quizRepository = quizRepository;
        this.quizDetailRepository = quizDetailRepository;
    }

    @Override
    public CategoryEntity createQuizCategory(CategoryDto categoryDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        CategoryEntity category = mapper.map(categoryDto, CategoryEntity.class);

        CategoryEntity result = categoryRepository.save(category);

        return result;
    }

    @Override
    public List<CategoryEntity> getCategoryByAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity updateCategory(CategoryDto categoryDto) {
        Optional<CategoryEntity> category = categoryRepository.findById(categoryDto.getCategoryNum());

        category.ifPresent(c->{
            c.setCategoryName(categoryDto.getCategoryName());
        });
        CategoryEntity result = category.get();
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        CategoryEntity category = mapper.map(categoryDto, CategoryEntity.class);
//
//        CategoryEntity result = categoryRepository.save(category);

        return result;
    }

    @Override
    public QuizEntity createQuiz(QuizDto quizDto){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuizEntity quiz = mapper.map(quizDto, QuizEntity.class);

        QuizDetailEntity quizDetail = new QuizDetailEntity();
        quizDetail.setQuizEntity(quiz);

        //퀴즈디테일 추가하면서 주테이블인 퀴즈리스트에 없으면 함께 넣어줌
        QuizDetailEntity result = quizDetailRepository.save(quizDetail);

        return result.getQuizEntity();
    }

    @Override
    public List<QuizEntity> getQuizByAll() {
        return quizRepository.findAll();
    }

    @Override
    public QuizDto getQuizByQuizNum(Integer quizNum) {
        Optional<QuizEntity> quizEntity = quizRepository.findById(quizNum);
        QuizDto[] quizDto = {null};
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        quizEntity.ifPresent(Q -> {
            quizDto[0] = mapper.map(Q, QuizDto.class);});

        return quizDto[0];
    }

    @Override
    public QuizEntity updateQuiz(QuizDto quizDto) {
        // 유저정보나 카테고리 변경시 업데이트되는지 확인 or 직접 함수호출
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        QuizEntity quiz = mapper.map(quizDto, QuizEntity.class);

        QuizEntity result = quizRepository.save(quiz);

//        Optional<QuizEntity> quiz = quizRepository.findById(quizDto.getQuizNum());
//
//        //QuizEntity의 quiznum 속성 setter 없앨거면 아래 코드 사용
//        quiz.ifPresent(Q -> {
//            Q.setQuizDetailEntity(quizDto.getQuizDetailEntity());
//            Q.setCategoryEntity(quizDto.getCategoryEntity());
//            Q.setUserEntity(quizDto.getUserEntity());
//            Q.setQuizContents(quizDto.getQuizContents());
//            Q.setQuizAnswer(quizDto.getQuizAnswer());
//            Q.setChoice1(quizDto.getChoice1());
//            Q.setChoice2(quizDto.getChoice2());
//            Q.setChoice3(quizDto.getChoice3());
//            Q.setChoice4(quizDto.getChoice4());
//            Q.setChoice5(quizDto.getChoice5());
//            Q.setQuizScore(quizDto.getQuizScore());
//        });

        return result;
    }

    @Override
    @Transactional
    public void deleteQuiz(Integer quizNum) {
        quizRepository.deleteById(quizNum);
    }

//    //필요없음
//    @Override
//    public QuizDetailEntity createQuizDetail(QuizDetailDto quizDetailDto){
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        QuizDetailEntity quizDetail = mapper.map(quizDetailDto, QuizDetailEntity.class);
//
//        quizDetailRepository.save(quizDetail);
//
//        return quizDetail;
//    }

    //    //필요없음
    @Override
    public List<QuizDetailEntity> getQuizDetailByAll() {
        return quizDetailRepository.findAll();
    }

    @Override
    @Transactional
    public QuizDetailEntity updateQuizDetailByQuizNum(Integer quizNum) {

        //나중에는 퀴즈히스토리로 받아와서 값 바꿀꺼임
        Optional<QuizDetailEntity> quizDetail = quizDetailRepository.findById(quizNum);

        quizDetail.ifPresent(qD->{
            qD.setTrialUserCount(1);
            qD.setAnswerUserCount(1);
            qD.setAnswerRate(1);
        });

//        quizDetail.setTrialUserCount(1);
//        quizDetail.setAnswerUserCount(1);
//        quizDetail.setAnswerRate(1);

        return quizDetail.get();
    }
}
