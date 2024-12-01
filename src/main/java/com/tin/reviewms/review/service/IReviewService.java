package com.tin.reviewms.review.service;

import com.tin.reviewms.review.model.Review;

import java.util.List;

public interface IReviewService {
    List<Review> getCompanyReviews(Long companyId);
    Review postReview(Long companyId, Review review);
    Review getReviewById(Long reviewId);
    Review putReviewById(Long reviewId, Review review);
    Boolean deleteReviewById(Long reviewId);
}
