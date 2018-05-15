package com.kpi.salon.services;

import com.kpi.salon.model.Request;

import java.util.List;

public interface IRequestService {
    void create(Request request);
    List<Request> findAllRequests();
    List<Request> findRequestsByMaster(Long id);
}
