package com.tin.reviewms.review.service;


import com.tin.reviewms.review.model.Review;
import com.tin.reviewms.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getCompanyReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review postReview(Long companyId, Review review) {
        if (companyId == null || review == null) {
            return null;
        }
        review.setCompanyId(companyId);
        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long reviewId) {
        if (reviewId == null) return null;
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) return null;
        return review;
    }

    @Override
    public Review putReviewById(Long reviewId, Review review) {
        if (reviewId == null) return null;
        Review foundReview = reviewRepository.findById(reviewId).orElse(null);
        if (foundReview == null) return null;
        foundReview.setDescription(review.getDescription());
        foundReview.setTitle(review.getTitle());
        foundReview.setRating(review.getRating());
        return reviewRepository.save(foundReview);
    }

    @Override
    public Boolean deleteReviewById(Long reviewId) {
        if (reviewId == null) return false;
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) return false;
        reviewRepository.delete(review);
        return true;
    }
}
