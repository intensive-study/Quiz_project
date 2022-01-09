package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void 로그인성공시_서버로부터_응답코드200_OK을_받는다() throws Exception{
        String json = mapper.writeValueAsString(new LoginDto("admin","admin"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/authenticate").contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void 로그인실패시_서버로부터_응답코드401_Unauthorized을_받는다() throws Exception{
        String json = mapper.writeValueAsString(new LoginDto("admin","123"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/authenticate").contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(requestBuilder).andExpect(status().isUnauthorized()).andDo(MockMvcResultHandlers.print());
    }
}
