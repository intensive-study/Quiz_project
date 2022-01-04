package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.SubmittedUserSolutionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc

public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("정상적인 Post요청시, 서버에서 상태코드 200을 받는다.")
    public void submitQuiz() throws Exception{
        String json = mapper.writeValueAsString(new SubmittedUserSolutionDto(1L, 1L, "2", false));
        String URL = "/quiz/user/solution";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
}
