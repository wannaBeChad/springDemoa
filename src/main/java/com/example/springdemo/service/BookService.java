package com.example.springdemo.service;


import com.example.springdemo.dto.BookDto;
import com.example.springdemo.entity.Book;
import com.example.springdemo.repository.BookRepository;
import com.example.springdemo.repository.UserRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository ) {
        this.bookRepository = bookRepository;
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

    public  List<BookDto> getAllBooks(){
        return bookRepository.findAll().stream().map((Book book)->convertToDto(book)).toList();
    }
    private BookDto convertToDto(Book book) {
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }
}
