package com.airbnb.controller;

import com.airbnb.model.Review;
import com.airbnb.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    
    private final ReviewService reviewService;
    
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<Review>> getReviewsByProperty(@PathVariable Long propertyId) {
        return ResponseEntity.ok(reviewService.getReviewsByProperty(propertyId));
    }
    
    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.createReview(review));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
