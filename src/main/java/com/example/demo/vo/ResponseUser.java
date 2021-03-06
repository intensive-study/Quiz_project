package com.example.demo.vo;

import com.example.demo.dto.AuthorityDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.Authority;
import com.example.demo.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String username;
    private String password;
    private String nickname;
    private Double totalScore;
    private Boolean activated;
    private Timestamp registerDate;
    private Set<AuthorityDto> authorityDtoSet;
}
