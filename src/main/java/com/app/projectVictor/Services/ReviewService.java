package com.app.projectVictor.Services;

import com.app.projectVictor.Entities.Review;
import com.app.projectVictor.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }
    public Review updateReview(Review updatedReview) {
        return reviewRepository.save(updatedReview);
    }

    public void deleteReview(int id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviewsByRecipeId(int recipeId) {
        return reviewRepository.findByRecipeId(recipeId);
    }

    public List<Review> getReviewsByUserId(int userId) {
        return reviewRepository.findByUserId(userId);
    }
    public Review getReviewById(int id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        return optionalReview.orElse(null);
    }
}
