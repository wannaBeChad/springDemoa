package com.example.springdemo.service;


import com.example.springdemo.dto.BookDto;
import com.example.springdemo.entity.Book;
import com.example.springdemo.entity.View;
import com.example.springdemo.exception.BookNotFoundException;
import com.example.springdemo.repository.BookRepository;
import com.example.springdemo.repository.UserRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;
    private final UserService userService;
    private final ViewService viewService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository, UserService userService, ViewService viewService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.viewService = viewService;
    }

    public void readAndInsertDataFromCSV(String filePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<Book> csvToBean = new CsvToBeanBuilder<Book>(reader)
                    .withType(Book.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(true)
                    .build();

            List<Book> books = csvToBean.parse();
            bookRepository.saveAll(books);
        } catch (IOException e) {
            // handle the exception
            throw new RuntimeException();
        }
    }

    public boolean saveABook(Book book){
        try {
            bookRepository.saveAndFlush(book);
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public  List<BookDto> getAllBookDtos(){
        return bookRepository.findAll().stream().map((Book book)->convertToDto(book)).toList();
    }
    public  List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    private BookDto convertToDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }

    public void incrementView(@PathVariable String bookId, @RequestParam String email) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId.toString()).orElseThrow(() -> new BookNotFoundException());
        bookRepository.save(book);
        View newUserView =View.builder().bookId(bookId).userId(userService.getUserByEmail(email).getId()).build();
        viewService.save(newUserView);
    }
    public BookDto fetchBookById(String id) {
        Book book =bookRepository.getById(id);
        return modelMapper.map(book,BookDto.class);
    }
}
