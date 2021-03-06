package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.QuizEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.IdNotExistException;
import com.example.demo.exception.UsernameNotExistException;
import com.example.demo.jpa.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.vo.*;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome test";
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(userDto));
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserEntity> getMyUserInfo(HttpServletRequest request){
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/users/ranking")
    public ResponseEntity<List<ResponseUserRank>> getUserRanking() {
        Iterable<UserEntity> userList = userService.getUserRanking();
        List<ResponseUserRank> resultList = new ArrayList<>();
        userList.forEach(v->{
            resultList.add(new ModelMapper().map(v, ResponseUserRank.class));
        });
        return ResponseEntity.status(HttpStatus.OK).body(resultList);
    }

    @PutMapping("/users/me")
    public ResponseEntity updateUser(@RequestBody @Valid RequestUser requestUser) {
        UserEntity userEntity = userService.updateUser(requestUser);
        ResponseUserEntity responseUser = new ResponseUserEntity(userEntity);
        System.out.println(requestUser.getUsername());

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }
}
