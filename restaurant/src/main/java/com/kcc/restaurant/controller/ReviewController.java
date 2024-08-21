package com.kcc.restaurant.controller;

import com.kcc.restaurant.bean.Review;
import com.kcc.restaurant.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;

    }

    @PostMapping("/review")
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        Review newReview = reviewService.createReview(review);
        return ResponseEntity.ok(newReview);
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable int id) {
        reviewService.deleteReviewById(id);
        return ResponseEntity.ok("Deleted review with id " + id);
    }
}
