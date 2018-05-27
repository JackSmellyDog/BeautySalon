package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.IClientDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Client;
import com.kpi.salon.utils.ResourcesManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ClientDao implements IClientDao {
    private static final Logger LOGGER = Logger.getLogger(ClientDao.class);
    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private Properties queries;

    public ClientDao() {
        init();
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        String query = queries.getProperty("sql.client.find.all");

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                clients.add(new Client(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                ));
            }

        } catch (NullPointerException | SQLException e) {
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
        String query = queries.getProperty("sql.client.find.by.id");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
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

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return client;
    }

    @Override
    public boolean insert(Client item) {
        String query = queries.getProperty("sql.client.insert");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(1, item.getLogin());
            preparedStatement.setString(2, item.getPassword());
            preparedStatement.executeUpdate();
            return true;

        } catch (NullPointerException | SQLException e) {
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
        String query = queries.getProperty("sql.client.find.by.username");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                client = new Client(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return client;
    }

    private void init() {
        try {
            queries = new ResourcesManager().getProperties("queries");
        } catch (IOException e) {
            LOGGER.error(String.format("Unable to download property file. %s", e.getMessage()), e);
        }
    }
}
