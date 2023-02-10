package com.example.springdemo.controller;

import com.example.springdemo.dto.BookDto;
import com.example.springdemo.entity.Book;
import com.example.springdemo.repository.BookRepository;
import com.example.springdemo.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class bookController {
    private final BookService bookService;


    public bookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/task/books")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBooks();
    }
}
