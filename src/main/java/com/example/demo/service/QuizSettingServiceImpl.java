package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.QuizDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.QuizDetailEntity;
import com.example.demo.entity.QuizEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.IdNotExistException;
import com.example.demo.exception.NameDuplicateException;
import com.example.demo.exception.ResultCode;
import com.example.demo.jpa.CategoryRepository;
import com.example.demo.jpa.QuizDetailRepository;
import com.example.demo.jpa.QuizRepository;
import com.example.demo.jpa.UserRepository;
import com.example.demo.vo.RequestQuiz;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class QuizSettingServiceImpl implements QuizService {

    CategoryRepository categoryRepository;
    QuizRepository quizRepository;
    QuizDetailRepository quizDetailRepository;
    UserRepository userRepository;

    @Autowired
    public QuizSettingServiceImpl(CategoryRepository categoryRepository, QuizRepository quizRepository, QuizDetailRepository quizDetailRepository, UserRepository userRepository){
        this.categoryRepository = categoryRepository;
        this.quizRepository = quizRepository;
        this.quizDetailRepository = quizDetailRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CategoryEntity createQuizCategory(CategoryDto categoryDto) throws NameDuplicateException {
//        categoryRepository.findByCategoryName(categoryDto.getCategoryName())
//                .ifPresent({throw new NameDuplicateException("name duplicated", ResultCode.NAME_DUPLICATION);});
        Optional <CategoryEntity> categoryEntity = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if(categoryEntity.isPresent()){
            throw new NameDuplicateException("name duplicated", ResultCode.NAME_DUPLICATION);
        }

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
    public QuizEntity createQuiz(RequestQuiz quizDto) throws IdNotExistException {
        QuizEntity quiz = new ModelMapper().map(quizDto, QuizEntity.class);

        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(quizDto.getCategoryNum());
        if(categoryEntity.isEmpty()){
            throw new IdNotExistException("category not exist", ResultCode.ID_NOT_EXIST);
        }

        Optional<UserEntity> userEntity = userRepository.findById(quizDto.getUserId());
        if(userEntity.isEmpty()){
            throw new IdNotExistException("user not exist", ResultCode.ID_NOT_EXIST);
        }

        quiz.setCategoryEntity(categoryEntity.get());
        quiz.setUserEntity(userEntity.get());

        //초기화
        QuizDetailEntity quizDetail = new QuizDetailEntity();
        quizDetail.setQuizEntity(quiz);
        quizDetail.setAnswerRate(0);
        quizDetail.setAnswerUserCount(0);
        quizDetail.setTrialUserCount(0);

        //퀴즈디테일 추가하면서 주테이블인 퀴즈리스트에 없으면 함께 넣어줌
        QuizDetailEntity quizDetailEntity = quizDetailRepository.save(quizDetail);

        QuizEntity result= quizDetailEntity.getQuizEntity();
        result.setQuizDetailEntity(quizDetailEntity);
        return result;
    }

    @Override
    public List<QuizEntity> getQuizByAll() {
        return quizRepository.findAll();
    }

    @Override
    public QuizEntity getQuizByQuizNum(Long quizNum) throws IdNotExistException {
        Optional<QuizEntity> quizEntity = quizRepository.findById(quizNum);

        if(quizEntity.isEmpty()){
            throw new IdNotExistException("quiz not exist", ResultCode.ID_NOT_EXIST);
        }
        System.out.println(quizEntity.get().getQuizNum());
        System.out.println(quizEntity.get().getQuizDetailEntity().getQuizNum());
        return quizEntity.get();
    }

    @Override
    public QuizEntity updateQuiz(RequestQuiz quizDto) throws IdNotExistException {
        // 유저정보나 카테고리 변경시 업데이트되는지 확인 or 직접 함수호출
//        QuizEntity quiz = new ModelMapper().map(quizDto, QuizEntity.class);

        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(quizDto.getCategoryNum());
        if(categoryEntity.isEmpty()){
            throw new IdNotExistException("category not exist", ResultCode.ID_NOT_EXIST);
        }

        Optional<QuizEntity> quiz = quizRepository.findById(quizDto.getQuizNum());

        if(quiz.isEmpty()){
            throw new IdNotExistException("quiz not exist", ResultCode.ID_NOT_EXIST);
        }

        if(quiz.get().getUserEntity().getUserId() != quizDto.getUserId()){
            throw new InvalidDataAccessApiUsageException("수정 권한이 없습니다.");
        }

        quiz.ifPresent(Q -> {
            Q.setCategoryEntity(categoryEntity.get());
            Q.setQuizContents(quizDto.getQuizContents());
            Q.setQuizAnswer(quizDto.getQuizAnswer());
            Q.setChoice1(quizDto.getChoice1());
            Q.setChoice2(quizDto.getChoice2());
            Q.setChoice3(quizDto.getChoice3());
            Q.setChoice4(quizDto.getChoice4());
            Q.setChoice5(quizDto.getChoice5());
            Q.setQuizScore(quizDto.getQuizScore());
        });

        return quiz.get();
    }

    @Override
    @Transactional
    public void deleteQuiz(Long quizNum) {
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
    public QuizDetailEntity updateQuizDetailByQuizNum(Long quizNum) throws IdNotExistException {

        //나중에는 퀴즈히스토리로 받아와서 값 바꿀꺼임
        Optional<QuizDetailEntity> quizDetail = quizDetailRepository.findById(quizNum);

        quizDetail.ifPresent(qD->{
            qD.setTrialUserCount(1);
            qD.setAnswerUserCount(1);
            qD.setAnswerRate(1);
        });

        if(quizDetail.isEmpty()){
            throw new IdNotExistException("quiz not exist", ResultCode.ID_NOT_EXIST);
        }

        return quizDetail.get();
    }
}
