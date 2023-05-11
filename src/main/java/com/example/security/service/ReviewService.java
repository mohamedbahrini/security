package com.example.security.service;

import com.example.security.entity.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(Review review);
    List<Review> getAllReviews();
    Review getReviewById(Review review) throws Exception;
    Review getReviewByBookId(Review review);
}
