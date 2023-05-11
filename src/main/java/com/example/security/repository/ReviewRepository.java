package com.example.security.repository;

import com.example.security.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findByBookId(String bookId);
}
