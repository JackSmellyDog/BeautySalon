package com.kpi.salon.dao;

import com.kpi.salon.model.Client;
import com.kpi.salon.model.Master;
import com.kpi.salon.model.Request;
import com.kpi.salon.model.Status;

import java.util.List;

public interface IRequestDao extends IDao<Request> {
    List<Request> findByMaster(Long id);
    List<Request> findByMaster(Master master);
    List<Request> findByClient(Long id);
    List<Request> findByClient(Client client);
    boolean updateStatus(Request request, Status status);
}
