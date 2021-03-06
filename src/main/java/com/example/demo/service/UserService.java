package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UsernameNotExistException;
import com.example.demo.vo.RequestUser;

import java.util.Optional;

//스프링 시큐리티 넣을 예정입니다.
public interface UserService{

    UserDto signup(UserDto userDto);
    UserDto getUserWithAuthorities(String username);
    Optional<UserEntity> getMyUserWithAuthorities();
    Iterable<UserEntity> getUsersByAll();
    Iterable<UserEntity> getUserRanking();
    UserEntity getUserByUsername(String username) throws UsernameNotExistException;
    // User 활성화 하기(관리자 모드)
    UserDto activateUser(String username);
    // User 비활성화 하기(관리자 모드)
    UserDto deactivateUser(String username);

    UserEntity updateUser(RequestUser requestUser);
}
