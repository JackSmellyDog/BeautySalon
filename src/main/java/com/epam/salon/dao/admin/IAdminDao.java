package com.epam.salon.dao.admin;

import com.epam.salon.dao.IDao;
import com.epam.salon.model.Admin;

public interface IAdminDao extends IDao<Admin> {
    Admin findByUsername(String username);
}
