package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
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
import com.example.demo.util.SecurityUtil;
import com.example.demo.vo.RequestQuiz;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    /**
     * 카테고리 조회
     */
    @Override
    public List<CategoryEntity> getCategoryByAll() {
        return categoryRepository.findAll();
    }

    /**
     * 카테고리 생성
     */
    @Override
    public CategoryEntity createQuizCategory(CategoryDto categoryDto) throws NameDuplicateException {
        Optional <CategoryEntity> categoryEntity = categoryRepository.findByCategoryName(categoryDto.getCategoryName());

        if(categoryEntity.isPresent()){
            throw new NameDuplicateException("name duplicated", ResultCode.NAME_DUPLICATION);
        }

        CategoryEntity category = new ModelMapper().map(categoryDto, CategoryEntity.class);
        CategoryEntity result = categoryRepository.save(category);

        return result;
    }

    /**
     * 카테고리 이름 변경
     */
    @Override
    public CategoryEntity updateCategory(CategoryDto categoryDto) throws IdNotExistException {
        CategoryEntity category = categoryRepository.findById(categoryDto.getCategoryNum())
                .orElseThrow(() -> new IdNotExistException("category not exist", ResultCode.ID_NOT_EXIST));

        category.setCategoryName(categoryDto.getCategoryName());

        return category;
    }

    /**
     * 카테고리 삭제
     */
    @Override
    public void deleteCategory(Long categoryNum) throws IdNotExistException {
        categoryRepository.findById(categoryNum)
                .orElseThrow(() -> new IdNotExistException("category not exist", ResultCode.ID_NOT_EXIST));

        quizRepository.updateCategoryNull(categoryNum);
        categoryRepository.deleteById(categoryNum);
    }

    /**
     * 퀴즈 조회
     */
    @Override
    public List<QuizEntity> getQuizByAll() {
        return quizRepository.findAll();
    }
    /**
     * 퀴즈 개별 조회
     */
    @Override
    public QuizEntity getQuizByQuizNum(Long quizNum) throws IdNotExistException {
        return quizRepository.findById(quizNum)
                .orElseThrow(() -> new IdNotExistException("quiz not exist", ResultCode.ID_NOT_EXIST));
    }
    /**
     * 카테고리 별 퀴즈 조회
     */
    @Override
    public List<QuizEntity> getQuizByCategoryNum(Long requestNum) throws IdNotExistException {
        Collection<QuizEntity> quizEntity;

        if (requestNum == 0){
            quizEntity = quizRepository.findByCategoryNull();
        }

        else {
            quizEntity = quizRepository.findByCategoryNum(requestNum);
        }

        if(quizEntity.isEmpty()){
            throw new IdNotExistException("quiz not exist", ResultCode.ID_NOT_EXIST);
        }
        return quizEntity.stream().collect(Collectors.toList());
    }

    /**
     * 퀴즈 생성
     */
    @Override
    public QuizEntity createQuiz(RequestQuiz quizDto) throws IdNotExistException {
        QuizEntity quiz = new ModelMapper().map(quizDto, QuizEntity.class);
        UserEntity user = SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                .orElseThrow(() -> new IdNotExistException("user not exist", ResultCode.ID_NOT_EXIST));

        CategoryEntity category = categoryRepository.findById(quizDto.getCategoryNum())
                .orElseThrow(() -> new IdNotExistException("category not exist", ResultCode.ID_NOT_EXIST));

        quiz.setCategoryEntity(category);
        quiz.setUserEntity(user);

        //(0, 0, 0)초기화 =>
        QuizDetailEntity quizDetail = new QuizDetailEntity(null, quiz, 0f, 0, 0);

        //퀴즈디테일 추가하면서 주테이블인 퀴즈리스트에 없으면 함께 넣어줌
        QuizDetailEntity quizDetailEntity = quizDetailRepository.save(quizDetail);

        QuizEntity result= quizDetailEntity.getQuizEntity();
        result.setQuizDetailEntity(quizDetailEntity);

        return result;
    }

    /**
     * 퀴즈 내용 변경
     */
    @Override
    public QuizEntity updateQuiz(RequestQuiz quizDto) throws IdNotExistException {
        QuizEntity quiz = quizRepository.findById(quizDto.getQuizNum())
                .orElseThrow(() -> new IdNotExistException("quiz not exist", ResultCode.ID_NOT_EXIST));

        UserEntity user = SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                .orElseThrow(() -> new IdNotExistException("user not exist", ResultCode.ID_NOT_EXIST));

        if(quiz.getUserEntity().getUserId() != user.getUserId()){
            throw new InvalidDataAccessApiUsageException("수정 권한이 없습니다.");
        }

        CategoryEntity categoryEntity = categoryRepository.findById(quizDto.getCategoryNum())
                .orElseThrow(() -> new IdNotExistException("category not exist", ResultCode.ID_NOT_EXIST));

        quiz.setCategoryEntity(categoryEntity);
        quiz.setQuizContents(quizDto.getQuizContents());
        quiz.setQuizAnswer(quizDto.getQuizAnswer());
        quiz.setChoice1(quizDto.getChoice1());
        quiz.setChoice2(quizDto.getChoice2());
        quiz.setChoice3(quizDto.getChoice3());
        quiz.setChoice4(quizDto.getChoice4());
        quiz.setChoice5(quizDto.getChoice5());
        quiz.setQuizScore(quizDto.getQuizScore());

        return quiz;
    }

    /**
     * 퀴즈 삭제
     */
    @Override
    @Transactional
    public void deleteQuiz(Long quizNum) throws IdNotExistException {
        QuizEntity quiz = quizRepository.findById(quizNum)
                .orElseThrow(() -> new IdNotExistException("quiz not exist", ResultCode.ID_NOT_EXIST));
        UserEntity user = SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                .orElseThrow(() -> new IdNotExistException("user not exist", ResultCode.ID_NOT_EXIST));

        if(quiz.getUserEntity().getUserId() != user.getUserId()){
            throw new InvalidDataAccessApiUsageException("삭제 권한이 없습니다.");
        }
        quizRepository.deleteById(quizNum);
    }

    @Override
    @Transactional
    public QuizDetailEntity updateQuizDetailByQuizNum(Long quizNum, boolean isSolved){

        Optional<QuizDetailEntity> quizDetail = quizDetailRepository.findById(quizNum);

        if(isSolved){
            quizDetail.ifPresent(qD->{
                qD.setTrialUserCount(qD.getTrialUserCount()+1);
                qD.setAnswerUserCount(qD.getAnswerUserCount()+1);
                qD.setAnswerRate((float)Math.floor((float)qD.getAnswerUserCount()/qD.getTrialUserCount()*100));
            });
        }

        else{
            quizDetail.ifPresent(qD->{
                qD.setTrialUserCount(qD.getTrialUserCount()+1);
                qD.setAnswerRate((float)Math.floor((float)qD.getAnswerUserCount()/qD.getTrialUserCount()*100));
            });
        }

        return quizDetail.get();
    }
}
