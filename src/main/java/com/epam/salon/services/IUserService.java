package com.epam.salon.services;

import com.epam.salon.exceptions.SuchUserIsExistException;
import com.epam.salon.model.Admin;
import com.epam.salon.model.Client;
import com.epam.salon.model.Master;
import com.epam.salon.model.User;

import java.util.List;

public interface IUserService {
    List<Admin> findAllAdmins();
    List<Client> findAllClients();
    List<Master> findAllMasters();
    User findByUsername(String username);
    User login(String login, String password);
    void register(String login, String password, String confirmationPassword) throws SuchUserIsExistException;
    void addMaster(String login, String password, String confirmationPassword, String name, String description) throws SuchUserIsExistException;
    boolean deleteMaster(Long id);
}
