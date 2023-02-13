package com.example.springdemo.repository;

import com.example.springdemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {
    Optional<Book> findByName(String name);
    Book getById(String Id);



}
