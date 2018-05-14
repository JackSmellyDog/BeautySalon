package com.kpi.salon.dao;

import com.kpi.salon.model.Client;

public interface IClientDao extends IDao<Client> {
    Client findByUsername(String username);
}
