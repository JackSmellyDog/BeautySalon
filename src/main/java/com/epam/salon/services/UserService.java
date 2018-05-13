package com.epam.salon.services;

import com.epam.salon.dao.DaoFactory;
import com.epam.salon.dao.admin.AdminDao;
import com.epam.salon.dao.client.ClientDao;
import com.epam.salon.dao.master.MasterDao;
import com.epam.salon.exceptions.InvalidUserDataException;
import com.epam.salon.exceptions.SuchUserIsExistException;
import com.epam.salon.model.Admin;
import com.epam.salon.model.Client;
import com.epam.salon.model.Master;
import com.epam.salon.model.User;
import org.apache.log4j.Logger;


import java.util.List;

import static com.epam.salon.dao.DaoFactory.Entity.*;

public class UserService implements IUserService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private AdminDao adminDao = (AdminDao) DaoFactory.create(ADMIN);
    private MasterDao masterDao = (MasterDao) DaoFactory.create(MASTER);
    private ClientDao clientDao = (ClientDao) DaoFactory.create(CLIENT);

    private SecurityService securityService = new SecurityService();
    private ValidationService validationService = new ValidationService();


    public User findByUsername(String username) {
        User user = adminDao.findByUsername(username);
        if (user != null) return user;

        user = masterDao.findByUsername(username);
        if (user != null) return user;

        return clientDao.findByUsername(username);
    }


    public User login(String login, String password) {
        if (!validationService.isUsernameValid(login) || !validationService.isPasswordValid(password))
            throw new InvalidUserDataException("Invalid data while loggin in");

        User user = findByUsername(login);

        if (user == null || !securityService.checkPassword(password, user.getPassword()))
            throw new InvalidUserDataException("Can't log in");

        return user;
    }

    public void register(String login, String password, String confirmationPassword) throws SuchUserIsExistException {
        if (!validationService.isUsernameValid(login))
            throw new InvalidUserDataException("Invalid username");

        if (!validationService.isPasswordValid(password))
            throw new InvalidUserDataException("Invalid password. It has to be more than 6 characters.");

        if (!validationService.isConfirmationPassMatched(password, confirmationPassword))
            throw new InvalidUserDataException("Passwords did not match");

        if (isAlreadyExist(login))
            throw new SuchUserIsExistException();

        Client client = new Client(login, securityService.encryptPassword(password));
        clientDao.insert(client);
    }

    private boolean isAlreadyExist(String username) {
        return findByUsername(username) != null;
    }

    public List<Admin> getAllAdmins() {
        return adminDao.findAll();
    }

    public List<Client> getAllClients() {
        return clientDao.findAll();
    }

    public List<Master> getAllMasters() {
        return masterDao.findAll();
    }
}
