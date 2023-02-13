package com.example.springdemo.service;

import com.example.springdemo.dto.ViewCsvDto;
import com.example.springdemo.entity.Book;
import com.example.springdemo.entity.User;
import com.example.springdemo.entity.View;
import com.example.springdemo.repository.BookRepository;
import com.example.springdemo.repository.ViewRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service
public class ViewService {
    public Map<String, Map<String, Object>> matrix = new HashMap<>();

    @Autowired
    private final ViewRepository viewRepository;

    @Autowired
    private final BookRepository bookRepository;
    private final UserService userService;

    public ViewService(ViewRepository viewRepository, BookRepository bookRepository, UserService userService) {
        this.viewRepository = viewRepository;
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    public void save(View view) {
        viewRepository.save(view);
    }


    public void readAndInsertViewDataFromCSV(String filePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<ViewCsvDto> csvToBean = new CsvToBeanBuilder<ViewCsvDto>(reader)
                    .withType(ViewCsvDto.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(true)
                    .build();

            List<ViewCsvDto> viewCsvDtos = csvToBean.parse();
            List<View> views = viewCsvDtos.stream().map(viewCsvDto -> viewCsvDtoToViewConverter(viewCsvDto) ).toList();
            viewRepository.saveAll(views);
        } catch (IOException e) {
            // handle the exception
            throw new RuntimeException();
        }
    }


    public void generateMatrix() {
        List<Book> listOfBooks = bookRepository.findAll();
        List<User> listOfUsers = userService.getAllUsers();
        for (User user:listOfUsers) {
            for (Book book:listOfBooks)
                putValue(user.getEmail(),book.getId(),getUserBookPairCount(book.getId(),user.getId()));
        }
    }

    private View viewCsvDtoToViewConverter(ViewCsvDto dto) {
        String dtoUserId =dto.getUser();
        Long userId =userService.getUserByEmail(dtoUserId).getId();
        return View.builder().bookId(dto.getBook()).userId(userId).build();

    }

    private int getUserBookPairCount(String bookId, Long userId){
        return viewRepository.getUserBookPairCount(bookId,userId);
    }
    private void putValue(String row, String col, Integer value) {
        Map<String, Object> rowData = matrix.get(row);
        if (rowData == null) {
            rowData = new HashMap<>();
            matrix.put(row, rowData);
        }
        rowData.put(col, value);
    }

    private Integer getValue(String row, String col) {
        Map<String, Object> rowData = matrix.get(row);
        if (rowData == null) {
            return null;
        }
        return (Integer) rowData.get(col);
    }


}
