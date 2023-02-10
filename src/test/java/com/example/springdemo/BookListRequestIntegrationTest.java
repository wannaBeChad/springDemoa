package com.example.springdemo;

import com.example.springdemo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookListRequestIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper= new ObjectMapper();


    private  BookService bookService;

    public BookListRequestIntegrationTest(BookService bookService) {
        this.bookService = bookService;
    }

    @Test
    void shouldReturn200WhenSendingRequestToLoginApi() throws Exception {
        mockMvc.perform(get("/task/books")
                        .contentType(MediaType.APPLICATION_JSON)  )
                .andExpect(status().isOk());
    }
}
