package com.app.projectVictor.Controller;

import com.app.projectVictor.Entities.Review;
import com.app.projectVictor.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable int id, @RequestBody Review updatedReview,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        Review existingReview = reviewService.getReviewById(id);

        if (existingReview == null) {
            return ResponseEntity.notFound().build();
        }

        if (!userDetails.getUsername().equals(existingReview.getUser().getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        updatedReview.setId(id);
        Review savedReview = reviewService.updateReview(updatedReview);
        return ResponseEntity.ok(savedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) {
        Review existingReview = reviewService.getReviewById(id);

        if (existingReview == null) {
            return ResponseEntity.notFound().build();
        }

        if (!userDetails.getUsername().equals(existingReview.getUser().getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
