package com.example.demo.vo;

import com.example.demo.dto.AuthorityDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
//    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private Double totalScore;
    private Boolean activated;
    private Set<AuthorityDto> authorityDtoSet;
//    private String email;
//    private String name;
//    private String userId;
//    private String totalScore;
}
