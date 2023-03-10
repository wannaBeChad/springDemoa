package com.example.springdemo;

import com.example.springdemo.dto.BookDto;
import com.example.springdemo.service.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookListRequestIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();



    @Test
    void shouldReturn200WhenSendingRequestToBookListApi() throws Exception {
        String json = mockMvc.perform(get("/task/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn()
                .getResponse()
                .getContentAsString();
     List<BookDto> values = mapper.readValue(json, new TypeReference<List<BookDto>>(){});
        assertThat(values).hasSize(3);
        assertThat(values.get(0).getId().equals("b1"));

    }
}
