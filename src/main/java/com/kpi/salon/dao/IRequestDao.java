package com.kpi.salon.dao;

import com.kpi.salon.model.Request;

import java.util.List;

public interface IRequestDao extends IDao<Request> {
    List<Request> findByMaster(Long id);
}
