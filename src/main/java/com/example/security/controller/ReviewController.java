package com.example.security.controller;

import com.example.security.entity.Review;
import com.example.security.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping("review")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }
}
