package com.kpi.salon.services.impl;

import com.kpi.salon.dao.DaoFactory;
import com.kpi.salon.dao.IRequestDao;
import com.kpi.salon.model.Request;
import com.kpi.salon.services.IRequestService;
import org.apache.log4j.Logger;

public class RequestService implements IRequestService {
    private static final Logger LOGGER = Logger.getLogger(RequestService.class);

    private IRequestDao requestDao = (IRequestDao) DaoFactory.create(DaoFactory.Entity.REQUEST);

    @Override
    public void create(Request request) {
        requestDao.insert(request);
    }
}
