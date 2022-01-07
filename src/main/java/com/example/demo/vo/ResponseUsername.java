package com.example.demo.vo;

import com.example.demo.dto.AuthorityDto;
import com.example.demo.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUsername {
    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private boolean activation;
    private Double totalScore;
    private Timestamp registerDate;
    private Set<AuthorityDto> authorityDtoSet;

    public ResponseUsername(UserEntity userEntity){
        this.userId = userEntity.getUserId();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.nickname = userEntity.getNickname();
        this.activation = userEntity.isActivated();
        this.totalScore = userEntity.getTotalScore();
        this.registerDate = userEntity.getRegisterDate();
        this.authorityDtoSet = userEntity.getAuthorities().stream()
                .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                .collect(Collectors.toSet());
    }
}
