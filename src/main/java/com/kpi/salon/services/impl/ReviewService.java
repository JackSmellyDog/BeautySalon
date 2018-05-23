package com.kpi.salon.services.impl;

import com.kpi.salon.dao.DaoFactory;
import com.kpi.salon.dao.impl.ReviewDao;
import com.kpi.salon.model.Request;
import com.kpi.salon.model.Review;
import com.kpi.salon.services.IReviewService;

import java.util.List;

import static com.kpi.salon.dao.DaoFactory.Entity.*;

public class ReviewService implements IReviewService {
    private ReviewDao reviewDao = (ReviewDao) DaoFactory.create(REVIEW);

    @Override
    public List<Review> findAllReviews() {
        return reviewDao.findAll();
    }

    @Override
    public Review findReviewById(Long id) {
        return reviewDao.findById(id);
    }

    @Override
    public boolean create(String text, Integer rating, Request request) {
        return reviewDao.insert(new Review(text, rating, request));
    }

}
