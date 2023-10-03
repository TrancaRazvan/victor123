package com.app.projectVictor;

import com.app.projectVictor.Controller.ReviewController;
import com.app.projectVictor.Entities.Review;
import com.app.projectVictor.Services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateReview() {
        Review sampleReview = new Review(1, 2);
        Review updatedReview = new Review(1, 4);
        when(reviewService.getReviewById(1)).thenReturn(sampleReview);

        SecurityContextHolder.clearContext();

        ResponseEntity<Review> result = reviewController.updateReview(1, updatedReview, null);

        verify(reviewService, times(1)).getReviewById(1);
        verify(reviewService, times(1)).updateReview(updatedReview);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(sampleReview, result.getBody());
    }

    @Test
    public void testDeleteReview() {
        Review sampleReview = new Review(1, 3);
        when(reviewService.getReviewById(1)).thenReturn(sampleReview);

        SecurityContextHolder.clearContext();

        ResponseEntity<Void> result = reviewController.deleteReview(1, null);

        verify(reviewService, times(1)).getReviewById(1);
        verify(reviewService, times(1)).deleteReview(1);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}
