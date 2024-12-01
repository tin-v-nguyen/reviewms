package com.tin.reviewms.review.controller;

import com.tin.reviewms.review.model.Review;
import com.tin.reviewms.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getCompanyReviews(@RequestParam Long companyId) {
        ResponseEntity<List<Review>> response;
        List<Review> reviews = reviewService.getCompanyReviews(companyId);
        response = reviews == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(reviews, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity<Review> postReview(@RequestParam Long companyId,
                                             @RequestBody Review review) {
        Review newReview = reviewService.postReview(companyId, review);
        ResponseEntity<Review> response;
        response = newReview == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(newReview, HttpStatus.CREATED);
        return response;
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        ResponseEntity<Review> response;
        Review foundReview = reviewService.getReviewById(reviewId);
        response = foundReview != null ? new ResponseEntity<>(foundReview, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> putReviewById(@PathVariable Long reviewId,
                                                @RequestBody Review review) {
        ResponseEntity<Review> response;
        Review foundReview = reviewService.putReviewById(reviewId, review);
        response = foundReview != null ? new ResponseEntity<>(foundReview, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId) {
        Boolean deleted = reviewService.deleteReviewById(reviewId);
        if (deleted) return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
