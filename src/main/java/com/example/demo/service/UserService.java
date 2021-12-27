package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

//스프링 시큐리티 넣을 예정입니다.
public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);

    Iterable<UserEntity> getUsersByAll();
    UserDto updateByUserId(UserDto userDto);
}
