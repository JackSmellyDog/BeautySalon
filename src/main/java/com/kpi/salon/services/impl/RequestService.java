package com.kpi.salon.services.impl;

import com.kpi.salon.dao.DaoFactory;
import com.kpi.salon.dao.IRequestDao;
import com.kpi.salon.dao.impl.ClientDao;
import com.kpi.salon.dao.impl.MasterDao;
import com.kpi.salon.model.Client;
import com.kpi.salon.model.Master;
import com.kpi.salon.model.Request;
import com.kpi.salon.services.IRequestService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

public class RequestService implements IRequestService {
    private static final Logger LOGGER = Logger.getLogger(RequestService.class);

    private IRequestDao requestDao = (IRequestDao) DaoFactory.create(DaoFactory.Entity.REQUEST);
    private ClientDao clientDao = (ClientDao) DaoFactory.create(DaoFactory.Entity.CLIENT);
    private MasterDao masterDao  = (MasterDao) DaoFactory.create(DaoFactory.Entity.MASTER);

    @Override
    public boolean create(LocalDateTime dateTime, Long clientId, Long masterId) {
        Client client = clientDao.findById(clientId);
        Master master = masterDao.findById(masterId);

        return requestDao.insert(new Request(dateTime, client, master));
    }

    @Override
    public List<Request> findAllRequests() {
        return requestDao.findAll();
    }

    @Override
    public List<Request> findRequestsByMaster(Long id) {
        return requestDao.findByMaster(id);
    }

    @Override
    public List<Request> findRequestsByClient(Long id) {
        return requestDao.findByClient(id);
    }
}
