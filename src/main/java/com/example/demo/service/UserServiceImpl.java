package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null)
            throw new UsernameNotFoundException(userId + ": not found");
        User user = new User(userEntity.getUserId(), userEntity.getPassword(), true, true, true, true, new ArrayList<>());
        return user;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);

        return null;
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
    public UserDto updateByUserId(UserDto userDto) {
        return null;
    }
}
