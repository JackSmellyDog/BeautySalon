package com.kpi.salon.dao;

import com.kpi.salon.model.Master;

public interface IMasterDao extends IDao<Master> {
    Master findByUsername(String username);
}
