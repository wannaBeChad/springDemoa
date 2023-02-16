package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class LoginApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn200WhenSendingRequestToLoginApi() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test1@check24.de\",\"password\":\"asd\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFailWhenSendingWrongRequestToLoginApi() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"Alex@adc.com\",\"password\":\"asd\"}"))
                .andExpect(status().isUnauthorized());
    }

}