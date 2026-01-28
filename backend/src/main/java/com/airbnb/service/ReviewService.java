package com.airbnb.service;

import com.airbnb.model.Review;
import com.airbnb.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    
    public List<Review> getReviewsByProperty(Long propertyId) {
        return reviewRepository.findByPropertyId(propertyId);
    }
    
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
    
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
