package com.example.springdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BookPlusSimilarDto {
    BookDto bookDto;
    List<Map.Entry<String, Double>> similarBooks;
}
