package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
//    private Long userId;
    private String username;
    private Double totalScore;
//    private String email;
//    private String name;
//    private String userId;
//    private String totalScore;
}
