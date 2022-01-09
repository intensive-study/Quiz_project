package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String userId;
    private String email;
    private String name;
    private String password;
    private String activation;
    private Integer score;
}
