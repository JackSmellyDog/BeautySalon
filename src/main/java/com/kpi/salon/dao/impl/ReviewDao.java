package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.IReviewsDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Review;
import org.apache.log4j.Logger;

import java.util.List;

public class ReviewDao implements IReviewsDao {
    private static final Logger LOGGER = Logger.getLogger(ReviewDao.class);
    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public List<Review> findAll() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Review findById(Long id) {
        return null;
    }

    @Override
    public boolean insert(Review item) {
        return false;
    }

}
