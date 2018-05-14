package com.kpi.salon.dao;

import com.kpi.salon.model.Admin;

public interface IAdminDao extends IDao<Admin> {
    Admin findByUsername(String username);
}
