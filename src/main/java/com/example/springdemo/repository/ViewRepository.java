package com.example.springdemo.repository;

import com.example.springdemo.entity.User;
import com.example.springdemo.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ViewRepository extends JpaRepository<View, Long> {
     Optional<View> findById(Long Id);

     @Query(value = "SELECT COUNT(*) FROM  views v where v.BOOK_ID = :book_id and v.user_id= :user_id ", nativeQuery = true)
     int getUserBookPairCount(@Param("book_id") String book_id, @Param("user_id") Long user_id);
}
