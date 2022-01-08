package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.vo.RequestQuiz;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class QuizControllerSettingTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    public String 로그인토큰(String username, String pwd) throws Exception {

        String json = mapper.writeValueAsString(new LoginDto(username, pwd));
        String URL = "/login";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json);

        MvcResult requestResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();

        String token = JsonPath.read(requestResult.getResponse().getContentAsString(), "$.token");
        System.out.println(token);

        return "Bearer " + token;
    }

    @Test
    @DisplayName("새로운 카테고리 생성시, 서버에서 상태코드 201(create)을 받는다.")
    public void PostCategory() throws Exception {
        String token = this.로그인토큰("admin", "admin");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("운영체제");
        String json = mapper.writeValueAsString(categoryDto);
        String URL = "/admin/category";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("중복된 이름의 카테고리 생성시, 서버에서 상태코드 406을 받는다.")
    public void 중복PostCategory() throws Exception {
        String token = this.로그인토큰("admin", "admin");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("OS");
        String json = mapper.writeValueAsString(categoryDto);
        String URL = "/admin/category";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("권한 없는 사용자가 카테고리 삭제 시, 서버에서 상태코드 403을 받는다.")
    public void InvalidDeleteCategory() throws Exception {
        String token = this.로그인토큰("user", "password");
        String URL = "/admin/category/2";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URL).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }

    //퀴즈가 있는 경우에도 삭제된다.
//    @Test
//    @DisplayName("해당 카테고리에 퀴즈가 있는 경우 삭제되지 않는다. 서버에서 상태코드 406을 받는다.")
//    public void IntegrityDeleteCategory() throws Exception {
//        String token = this.로그인토큰("admin", "admin");
//
//        String URL = "/admin/category/delete/2";
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URL).header(HttpHeaders.AUTHORIZATION, token);
//        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
//    }

    @Test
    @DisplayName("admin 권한으로 삭제하고 서버에서 상태코드 200을 받는다.")
    public void DeleteCategory() throws Exception {
        String token = this.로그인토큰("admin", "admin");

        String URL = "/admin/category/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URL).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("퀴즈 추가시 서버에서 상태코드 201(create)을 받는다.")
    public void CreateQuiz() throws Exception {
        String token = this.로그인토큰("user", "password");
        RequestQuiz requestQuiz = RequestQuiz.builder()
                .quizContents("내용")
                .quizAnswer("choice1")
                .quizScore(5)
                .userId(1L)
                .categoryNum(2L)
                .choice1("ooooooo")
                .choice3("xxxxxxx")
                .build();
        String json = mapper.writeValueAsString(requestQuiz);
        String URL = "/quiz";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    @DisplayName("퀴즈 추가 시 필수 값 누락되면 서버에서 상태코드 400을 받는다.")
    public void 필수값누락CreateQuiz() throws Exception {
        String token = this.로그인토큰("user", "password");
        RequestQuiz requestQuiz = RequestQuiz.builder()
                .quizContents("내용")
                .quizScore(5)
                .userId(1L)
                .build();
        String json = mapper.writeValueAsString(requestQuiz);
        String URL = "/quiz";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("퀴즈 추가 시 무결성 위반된 값을 넣으면 서버에서 상태코드 404을 받는다.")
    public void 무결성위반CreateQuiz() throws Exception {
        String token = this.로그인토큰("user", "password");
        RequestQuiz requestQuiz = RequestQuiz.builder()
                .quizContents("내용")
                .quizAnswer("choice1")
                .quizScore(5)
                .userId(1L)
                .categoryNum(7L)
                .choice1("ooooooo")
                .build();
        String json = mapper.writeValueAsString(requestQuiz);
        String URL = "/quiz";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("퀴즈 업데이트 성공시 서버에서 상태코드 201(create)을 받는다.")
    public void UpdateQuiz() throws Exception {
        String token = this.로그인토큰("user", "password");
        RequestQuiz requestQuiz = RequestQuiz.builder()
                .quizNum(2L)
                .quizContents("내용")
                .quizAnswer("choice1")
                .quizScore(5)
                .userId(1L)
                .categoryNum(2L)
                .choice1("oo")
                .build();
        String json = mapper.writeValueAsString(requestQuiz);
        String URL = "/quiz";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("수정 권한 없는 사용자면 서버에서 상태코드 203(정보권한없음)을 받는다.")
    public void InvalidUpdateQuiz() throws Exception {
        String token = this.로그인토큰("user", "password");
        RequestQuiz requestQuiz = RequestQuiz.builder()
                .quizNum(2L)
                .quizContents("내용")
                .quizAnswer("choice1")
                .quizScore(5)
                .userId(2L)
                .categoryNum(2L)
                .choice1("oo")
                .build();
        String json = mapper.writeValueAsString(requestQuiz);
        String URL = "/quiz";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().isNonAuthoritativeInformation()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("퀴즈 변경시 필수 값 누락되면 서버에서 상태코드 400을 받는다.")
    public void 필수값누락UpdateQuiz() throws Exception {
        String token = this.로그인토큰("user", "password");
        RequestQuiz requestQuiz = RequestQuiz.builder()
                .quizContents("내용")
                .quizScore(5)
                .userId(1L)
                .build();
        String json = mapper.writeValueAsString(requestQuiz);
        String URL = "/quiz";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    @DisplayName("퀴즈 추가 시 foreign key에 없는 값을 넣으면 서버에서 상태코드 404을 받는다.")
    public void 무결성위반UpdateQuiz() throws Exception {
        String token = this.로그인토큰("user", "password");
        RequestQuiz requestQuiz = RequestQuiz.builder()
                .quizNum(2L)
                .quizContents("내용")
                .quizAnswer("choice1")
                .quizScore(5)
                .userId(5L)
                .categoryNum(5L)
                .choice1("oo")
                .build();
        String json = mapper.writeValueAsString(requestQuiz);
        String URL = "/quiz";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("변경할 퀴즈가 존재하지 않으면 서버에서 상태코드 404을 받는다.")
    public void IdNotExistUpdateQuiz() throws Exception {
        String token = this.로그인토큰("user", "password");
        RequestQuiz requestQuiz = RequestQuiz.builder()
                .quizNum(7L)
                .quizContents("내용")
                .quizAnswer("choice1")
                .quizScore(5)
                .userId(1L)
                .categoryNum(2L)
                .choice1("o")
                .build();
        String json = mapper.writeValueAsString(requestQuiz);
        String URL = "/quiz";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }
}
