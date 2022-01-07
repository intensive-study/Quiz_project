package com.example.demo.user;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.SubmittedUserSolutionDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testA(){
        Assertions.assertThat(5).isEqualTo(5);
    }

    @Test
    @DisplayName("정상적인 Post요청시, 서버에서 상태코드 200을 받는다.")
    public void 회원가입() throws Exception{
        UserDto userDto = new UserDto();
        userDto.setUsername("park");
        userDto.setPassword("helloworld");
        userDto.setNickname("hahahoho");
        String json = mapper.writeValueAsString(userDto);
//        String json = mapper.writeValueAsString(new SubmittedUserSolutionDto(1L, 1L, "2", false));
        String URL = "/signup";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

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
        String URL = "/admin/category/create";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON).content(json).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("정상적인 Put요청시, 서버에서 상태코드 200을 받는다.")
    public void 유저비활성화() throws Exception{
        String token = this.로그인토큰("admin", "admin");
        String URL = "/admin/deactivate/user";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("정상적인 Put요청시, 서버에서 상태코드 200을 받는다.")
    public void 유저활성화() throws Exception{
        String token = this.로그인토큰("admin", "admin");
        String URL = "/admin/activate/user";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URL).header(HttpHeaders.AUTHORIZATION, token);
        mockMvc.perform(requestBuilder).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void 회원가입(){
//        UserDto userDto = new UserDto();
//        userDto.setUsername("박현우");
//        userDto.setPassword("helloworld");
//        userDto.setNickname("하하호호");
//    }
}
