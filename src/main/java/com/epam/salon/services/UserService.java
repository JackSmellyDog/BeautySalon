package com.epam.salon.services;

import com.epam.salon.dao.DaoFactory;
import com.epam.salon.dao.admin.AdminDao;
import com.epam.salon.dao.client.ClientDao;
import com.epam.salon.dao.master.MasterDao;
import com.epam.salon.exceptions.SuchUserIsExistException;
import com.epam.salon.model.Admin;
import com.epam.salon.model.Client;
import com.epam.salon.model.User;


import java.util.List;

import static com.epam.salon.dao.DaoFactory.Entity.*;

public class UserService implements IUserService {
    private AdminDao adminDao = (AdminDao) DaoFactory.create(ADMIN);
    private MasterDao masterDao = (MasterDao) DaoFactory.create(MASTER);
    private ClientDao clientDao = (ClientDao) DaoFactory.create(CLIENT);

    private SecurityService securityService = new SecurityService();

    /*******
     * I don't like this method
    * */

    public User findByUsername(String username){
        User user = adminDao.findByUsername(username);
        if (user != null) return user;

        user = masterDao.findByUsername(username);
        if (user != null) return user;

        return clientDao.findByUsername(username);
    }


    public boolean login(String login, String password) {
        User user = findByUsername(login);

        if (user == null) {
            return false;
        }

        return securityService.checkPasswords(password, user.getPassword());
    }

    public void register(String login, String password) throws SuchUserIsExistException {
        if (isAlreadyExist(login)) {
            throw new SuchUserIsExistException();
        }

        // maybe add confirm password

        password = securityService.encryptPassword(password);

        Client client = new Client(login, password);
        clientDao.insert(client);
    }

    private boolean isAlreadyExist(String username) {
        return findByUsername(username) != null;
    }

    public List<Admin> getAll() {
        return adminDao.getAll();
    }
}
