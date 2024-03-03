package com.fastcampus.miniorderproject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fastcampus.miniorderproject.model.UserModel;
import java.util.HashMap;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    private static final Logger log = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("user controller test - create")
    void userControllerTest() throws Exception {

        //given
        UserModel reqUserModel = new UserModel();
        reqUserModel.setName("testName");
        reqUserModel.setNickName("nickName");
        reqUserModel.setEmail("email");
        reqUserModel.setPassword("password");
        reqUserModel.setAuthority("회원");
        reqUserModel.setStatus("1");

        //when
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(reqUserModel);

        //then
        String returnValue = mockMvc.perform(post("/user")
                    .content(jsonBody)
                    .characterEncoding("UTF-8")
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.info("return value : "+ returnValue);

        // user CreateData 타입 에러로 인한 HashMap 형식 반환
        HashMap<String,Object> returnModel = objectMapper.readValue(returnValue, HashMap.class);
        assertThat(returnModel).isNotNull(); // 저장된 유저가 null이 아닌지 확인
        assertThat(returnModel.get("id")).isNotNull(); // 유저의 ID가 null이 아닌지 확인
        assertThat(returnModel.get("name")).isEqualTo(reqUserModel.getName()); // 유저명이 일치하는지 확인
        assertThat(returnModel.get("email")).isEqualTo(reqUserModel.getEmail()); // 이메일이 일치하는지 확인

    }
}
