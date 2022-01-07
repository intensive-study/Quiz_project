package com.example.demo.dto;

import com.example.demo.entity.Authority;
import com.example.demo.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

//    @NotNull
//    @Size(min = 3, max = 50)
//    private String userId;
//    private String email;
    private String username;
    private String password;
    private String nickname;
    private Boolean activated;
    private Double totalScore;
    private Set<AuthorityDto> authorityDtoSet;

    public static UserDto from(UserEntity user){
        if(user == null) return null;
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .activated(user.isActivated())
                .totalScore(user.getTotalScore())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
