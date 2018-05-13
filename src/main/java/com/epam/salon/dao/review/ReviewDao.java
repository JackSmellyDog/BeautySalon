package com.epam.salon.dao.review;

import com.epam.salon.dao.request.IRequestDao;
import com.epam.salon.datasource.ConnectionManager;
import com.epam.salon.model.Request;

import java.util.List;

public class ReviewDao implements IRequestDao {
    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public List<Request> findAll() {
        return null;
    }

    @Override
    public void insert(Request item) {

    }
}
