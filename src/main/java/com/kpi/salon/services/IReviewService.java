package com.kpi.salon.services;

import com.kpi.salon.model.Request;
import com.kpi.salon.model.Review;

import java.util.List;

public interface IReviewService {
    List<Review> findAllReviews();
    Review findReviewById(Long id);
    boolean create(String text, Integer rating, Request request);
}
