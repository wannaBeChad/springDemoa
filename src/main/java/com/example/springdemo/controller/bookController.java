package com.example.springdemo.controller;

import com.example.springdemo.dto.BookDto;
import com.example.springdemo.dto.BookPlusSimilarDto;
import com.example.springdemo.exception.BookNotFoundException;
import com.example.springdemo.service.BookService;
import com.example.springdemo.service.ViewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class bookController {
    private final BookService bookService;
    private final ViewService viewService;



    public bookController(BookService bookService, ViewService viewService) {
        this.bookService = bookService;
        this.viewService = viewService;
    }
    @GetMapping("/task/books")
    public List<BookDto> getAllBooks(){
        return bookService.getAllBookDtos();
    }

    @PutMapping("/task/book/{bookId}")
    public BookPlusSimilarDto viewBook(@PathVariable String bookId /*,@RequestParam String email*/) throws BookNotFoundException {
        bookService.incrementView(bookId,"test1@check24.de");
        List<Map.Entry<String, Double>> similarList = viewService.getTopNSortedListOfCosineSimScores(bookId,2);
        BookDto bookDto= bookService.fetchBookById(bookId);

        BookPlusSimilarDto dto= BookPlusSimilarDto.builder().bookDto(bookDto).similarBooks(similarList).build();
        return dto;
    }
}
