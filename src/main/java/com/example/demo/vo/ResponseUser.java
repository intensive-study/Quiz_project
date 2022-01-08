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

    public ResponseUser(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.nickname = userEntity.getNickname();
        this.totalScore = userEntity.getTotalScore();
        this.activated = userEntity.isActivated();
        this.registerDate = userEntity.getRegisterDate();
        this.authorityDtoSet = userEntity.getAuthorities().stream()
                .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                .collect(Collectors.toSet());
    }
}
