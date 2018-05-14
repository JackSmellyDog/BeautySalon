package com.epam.salon.dao.review;

import com.epam.salon.dao.request.IRequestDao;
import com.epam.salon.datasource.ConnectionManager;
import com.epam.salon.model.Request;
import org.apache.log4j.Logger;

import java.util.List;

public class ReviewDao implements IRequestDao{
    private static final Logger LOGGER = Logger.getLogger(ReviewDao.class);
    private ConnectionManager connectionManager = ConnectionManager.getInstance();

    @Override
    public List<Request> findAll() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean findById(Long id) {
        return false;
    }

    @Override
    public void insert(Request item) {

    }
}
