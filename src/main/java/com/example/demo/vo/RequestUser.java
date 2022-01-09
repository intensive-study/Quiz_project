package com.example.demo.vo;

import com.example.demo.dto.AuthorityDto;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Set;

@Data
public class RequestUser {

    @NotNull(message = "이름은 null값이 될 수 업습니다")
    @Size(max = 10, message = "이름은 최대 10글자까지 가능합니다.")
    private String username;

    @NotNull(message = "비밀번호는 null값이 될 수 없습니다")
    @Size(min=8, message = "비밀번호는 최소 8글자 이상 입력해 주세요")
    private String password;

    private String nickname;
    private Boolean activated;
}
