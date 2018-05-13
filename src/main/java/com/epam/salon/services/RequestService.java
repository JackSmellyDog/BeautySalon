package com.epam.salon.services;

import com.epam.salon.dao.DaoFactory;
import com.epam.salon.dao.request.IRequestDao;
import com.epam.salon.model.Request;
import org.apache.log4j.Logger;

public class RequestService implements IRequestService {
    private static final Logger LOGGER = Logger.getLogger(RequestService.class);

    private IRequestDao requestDao = (IRequestDao) DaoFactory.create(DaoFactory.Entity.REQUEST);

    @Override
    public void create(Request request) {
        requestDao.insert(request);
    }
}
