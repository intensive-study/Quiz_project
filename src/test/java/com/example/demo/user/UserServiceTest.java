package com.example.demo.user;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Test
    public void testA(){
        assert (20 == 20);
    }

    @Test
    public void testB(){
        System.out.println(5);
    }

//    @Autowired
//    private final UserService userService;
//
//    @Autowired
//    public UserServiceTest(UserService userService){
//        this.userService = userService;
//    }
//
//    @Test
//    UserEntity 회원가입(){
//        UserDto userDto = new UserDto();
//        userDto.setUserId("gusdn3477");
//        userDto.setPassword("helloworld");
//
//        userService.createUser(userDto);
//        return null;
//    }
}
