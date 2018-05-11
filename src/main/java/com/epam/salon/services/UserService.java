package com.epam.salon.services;

import com.epam.salon.dao.DaoFactory;
import com.epam.salon.dao.admin.AdminDao;
import com.epam.salon.dao.client.ClientDao;
import com.epam.salon.dao.master.MasterDao;
import com.epam.salon.model.Admin;
import com.epam.salon.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.epam.salon.dao.DaoFactory.Entity.*;

public class UserService implements IUserService {
    private AdminDao adminDao = (AdminDao) DaoFactory.create(ADMIN);
    private MasterDao masterDao = (MasterDao) DaoFactory.create(MASTER);
    private ClientDao clientDao = (ClientDao) DaoFactory.create(CLIENT);


    // TODO
    public List<Admin> getAll() {
        return adminDao.getAll();
    }
    public Admin findByUsername(String username){
        return adminDao.findByUsername(username);
    }

}
