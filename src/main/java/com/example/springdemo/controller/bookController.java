package com.example.springdemo.controller;

import com.example.springdemo.dto.BookDto;
import com.example.springdemo.exception.BookNotFoundException;
import com.example.springdemo.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class bookController {
    private final BookService bookService;



    public bookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/task/books")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBookDtos();
    }

    @PutMapping("/book/{bookId}/view")
    public BookDto viewBook(@PathVariable String bookId, @RequestParam String email) throws BookNotFoundException {
        bookService.incrementView(bookId,email);
        return bookService.fetchBookById(bookId);
    }
}
