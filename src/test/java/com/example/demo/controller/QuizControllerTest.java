package com.example.demo.controller;

import com.example.demo.dto.SubmittedUserSolutionDto;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void 정상적인_Post_요청시_서버로부터_상태코드200을_받는다() throws Exception{
        String json = mapper.writeValueAsString(new SubmittedUserSolutionDto(1L, 1L, "4"));
        String URL = "/quiz/user/solution";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 틀린정답을_제출했을때_서버로부터_상태코드200을_받는다() throws Exception{

        String json = mapper.writeValueAsString(new SubmittedUserSolutionDto(1L, 1L, "2"));
        String URL = "/quiz/user/solution";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 이미해결한문제를_다시_제출했을때_서버로부터_상태코드403를_받는다() throws Exception{

        String json = mapper.writeValueAsString(new SubmittedUserSolutionDto(1L, 1L, "4"));
        String URL = "/quiz/user/solution";
        String expectBySolved = "$.solved";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(requestBuilder);
        mockMvc.perform(requestBuilder).andExpect(status().isForbidden());
    }

    @Test
    public void 틀린정답을_제출했을때_solved가_false이다() throws Exception{

        String json = mapper.writeValueAsString(new SubmittedUserSolutionDto(1L, 1L, "2"));
        String URL = "/quiz/user/solution";
        String expectBySolved = "$.solved";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(requestBuilder).andExpect(jsonPath(expectBySolved, "false").exists());
    }

    @Test
    public void 올바른정답을_제출했을때_solved가_true다() throws Exception{

        String json = mapper.writeValueAsString(new SubmittedUserSolutionDto(1L, 1L, "4"));
        String URL = "/quiz/user/solution";
        String expectBySolved = "$.solved";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(requestBuilder).andExpect(jsonPath(expectBySolved, "true").exists());
    }

}
