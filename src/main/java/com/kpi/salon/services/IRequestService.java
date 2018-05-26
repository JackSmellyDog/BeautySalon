package com.kpi.salon.services;

import com.kpi.salon.model.Request;

import java.time.LocalDateTime;
import java.util.List;

public interface IRequestService {
    boolean create(LocalDateTime dateTime, Long clientId, Long masterId);
    List<Request> findAllRequests();
    List<Request> findRequestsByMaster(Long id);

    List<Request> findRequestsByClient(Long id);
    Request findRequestById(Long id);

    boolean markAsDone(Request req);
    boolean markAsCanceled(Request req);
}
