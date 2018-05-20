package com.kpi.salon.services;

import com.kpi.salon.exceptions.SuchUserIsExistException;
import com.kpi.salon.model.Admin;
import com.kpi.salon.model.Client;
import com.kpi.salon.model.Master;
import com.kpi.salon.model.User;

import java.util.List;

public interface IUserService {
    List<Admin> findAllAdmins();
    List<Client> findAllClients();
    List<Master> findAllMasters();
    User findByUsername(String username);
    User login(String login, String password);
    boolean register(String login, String password, String confirmationPassword) throws SuchUserIsExistException;
    boolean addMaster(String login, String password, String confirmationPassword, String name, String description) throws SuchUserIsExistException;
    boolean deleteMaster(Long id);
    Master findMasterById(Long id);
}
