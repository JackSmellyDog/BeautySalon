package com.kpi.salon.dao;

import com.kpi.salon.model.Request;
import com.kpi.salon.model.Review;

public interface IReviewsDao extends IDao<Review> {
    Review findByRequestId(Request request);
}
