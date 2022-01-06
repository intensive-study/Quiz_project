package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Authority;
import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.UserRepository;
import com.example.demo.util.SecurityUtil;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    Environment env;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, Environment env){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.env = env;
    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public UserDto signup(UserDto userDto){
        if(userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null){
            throw new DuplicateRequestException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        UserEntity user= UserEntity.builder()
                .username(userDto.getUsername())
                .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return UserDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username){
        return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities(){
        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null));
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
//        예외처리 해줘야 함
//        if(userEntity == null){
//
//        }
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        return userDto;
    }

    //전체 사용자 조회(랭크 보여줄 때 사용)
    @Override
    public Iterable<UserEntity> getUsersByAll() {
        return userRepository.findAll();
    }

    @Override
    public Iterable<UserEntity> getUserRanking() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "totalScore"));
    }
    @Override
    public UserDto updateByUserId(UserDto userDto) {
        return null;
    }
}