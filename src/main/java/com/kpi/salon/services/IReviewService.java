package com.kpi.salon.services;

import com.kpi.salon.model.Request;
import com.kpi.salon.model.Review;

import java.util.List;

public interface IReviewService {
    List<Review> findAllReviews();
    Review findReviewById(Long id);
    Review findReviewByRequest(Request request);
    boolean create(String text, Integer rating, Request request);
    boolean updateTextAndRating(Long id, String text, Integer rating);
}
