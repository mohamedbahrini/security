package com.example.security.service;

import com.example.security.entity.Review;
import com.example.security.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository repository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Review saveReview(Review review) {
        return repository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return repository.findAll();
    }

    @Override
    public Review getReviewById(Review review) throws Exception {
        return repository.findById(review.getId()).orElseThrow(Exception::new);
    }

    @Override
    public Review getReviewByBookId(Review review) {
        return repository.findByBookId(review.getBookId());
    }
}
