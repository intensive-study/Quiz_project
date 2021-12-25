package com.example.demo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {

    @NotNull(message = "userId cannot be null")
    @Size(min=4, message="유저 아이디는 최소 4글자 이상이어야 합니다.")
    private String userId;

    @NotNull(message = "이름은 null값이 될 수 업습니다")
    @Size(max = 10, message = "이름은 최대 10글자까지 가능합니다.")
    private String name;

    @NotNull(message = "비밀번호는 null값이 될 수 없습니다")
    @Size(min=8, message = "비밀번호는 최소 8글자 이상 입력해 주세요")
    private String password;

    @NotNull(message = "비밀번호는 null값이 될 수 없습니다")
    @Size(min=8, message = "비밀번호는 최소 8글자 이상 입력해 주세요")
    private String confirmPassword;

}
