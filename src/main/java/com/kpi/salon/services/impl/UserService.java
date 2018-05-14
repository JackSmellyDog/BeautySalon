package com.kpi.salon.services.impl;

import com.kpi.salon.dao.DaoFactory;
import com.kpi.salon.dao.impl.AdminDao;
import com.kpi.salon.dao.impl.ClientDao;
import com.kpi.salon.dao.impl.MasterDao;
import com.kpi.salon.exceptions.InvalidUserDataException;
import com.kpi.salon.exceptions.SuchUserIsExistException;
import com.kpi.salon.model.Admin;
import com.kpi.salon.model.Client;
import com.kpi.salon.model.Master;
import com.kpi.salon.model.User;
import com.kpi.salon.services.IUserService;
import org.apache.log4j.Logger;


import java.util.List;

public class UserService implements IUserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private AdminDao adminDao = (AdminDao) DaoFactory.create(DaoFactory.Entity.ADMIN);
    private MasterDao masterDao = (MasterDao) DaoFactory.create(DaoFactory.Entity.MASTER);
    private ClientDao clientDao = (ClientDao) DaoFactory.create(DaoFactory.Entity.CLIENT);

    private SecurityService securityService = new SecurityService();
    private ValidationService validationService = new ValidationService();

    @Override
    public User findByUsername(String username) {
        User user = adminDao.findByUsername(username);
        if (user != null) return user;

        user = masterDao.findByUsername(username);
        if (user != null) return user;

        return clientDao.findByUsername(username);
    }

    @Override
    public User login(String login, String password) {
        if (!validationService.isUsernameValid(login) || !validationService.isPasswordValid(password))
            throw new InvalidUserDataException("Invalid data while loggin in");

        User user = findByUsername(login);

        if (user == null || !securityService.checkPassword(password, user.getPassword()))
            throw new InvalidUserDataException("Can't log in");

        return user;
    }

    @Override
    public void register(String login, String password, String confirmationPassword) throws SuchUserIsExistException {
        validate(login, password, confirmationPassword);
        Client client = new Client(login, securityService.encryptPassword(password));
        clientDao.insert(client);
    }

    @Override
    public void addMaster(String login, String password, String confirmationPassword, String name, String description)
            throws SuchUserIsExistException {
        validate(login, password, confirmationPassword);
        masterDao.insert(new Master(login, password, name, description));
    }

    @Override
    public boolean deleteMaster(Long id) {
        return masterDao.deleteById(id);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminDao.findAll();
    }

    @Override
    public List<Client> findAllClients() {
        return clientDao.findAll();
    }

    @Override
    public List<Master> findAllMasters() {
        return masterDao.findAll();
    }

    private boolean isAlreadyExist(String username) {
        return findByUsername(username) != null;
    }

    private void validate(String login, String password, String confirmationPassword) throws SuchUserIsExistException {
        if (!validationService.isUsernameValid(login))
            throw new InvalidUserDataException("Invalid username");

        if (!validationService.isPasswordValid(password))
            throw new InvalidUserDataException("Invalid password. It has to be more than 6 characters.");

        if (!validationService.isConfirmationPassMatched(password, confirmationPassword))
            throw new InvalidUserDataException("Passwords did not match");

        if (isAlreadyExist(login))
            throw new SuchUserIsExistException();
    }

}
