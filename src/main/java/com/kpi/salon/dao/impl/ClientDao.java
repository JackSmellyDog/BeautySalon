package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.IClientDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Client;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements IClientDao {
    private static final Logger LOGGER = Logger.getLogger(ClientDao.class);


    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String BY_USERNAME_QUERY = "SELECT * FROM beauty_clients WHERE login='%s'";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM beauty_clients WHERE id=?";
    private static final String INSERT_USER_QUERY = "INSERT INTO beauty_clients (login, password) VALUES (?, ?)";
    private static final String ALL_USERS_QUERY = "SELECT * FROM beauty_clients";

    private static final int LOGIN_COLUMN = 1;
    private static final int PASSWORD_COLUMN = 2;


    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(ALL_USERS_QUERY);

            while (resultSet.next()){
                clients.add(new Client(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                ));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return clients;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Client findById(Long id) {
        Client client = null;
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                client = new Client(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return client;
    }

    @Override
    public boolean insert(Client item) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY)
        ){
            preparedStatement.setString(LOGIN_COLUMN, item.getLogin());
            preparedStatement.setString(PASSWORD_COLUMN, item.getPassword());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean update(Client item) {
        return false;
    }

    @Override
    public Client findByUsername(String username) {
        Client client = null;
        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){

            ResultSet resultSet = statement.executeQuery(String.format(
                    BY_USERNAME_QUERY,
                    username
            ));

            if (resultSet.next()){
                client = new Client(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return client;
    }
}
