package com.epam.salon.dao.client;

import com.epam.salon.datasource.ConnectionManager;
import com.epam.salon.model.Admin;
import com.epam.salon.model.Client;

import java.sql.*;
import java.util.List;

public class ClientDao implements IClientDao {
    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String BY_USERNAME_QUERY = "SELECT * FROM beauty_clients WHERE login='%s'";
    private static final String INSERT_USER_QUERY = "INSERT INTO beauty_clients (login, password) VALUES (?, ?)";

    private static final int LOGIN_COLUMN = 1;
    private static final int PASSWORD_COLUMN = 2;


    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Client getById(long id) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }

    @Override
    public void insert(Client item) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY)
        ){
            preparedStatement.setString(LOGIN_COLUMN, item.getLogin());
            preparedStatement.setString(PASSWORD_COLUMN, item.getPassword());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace(); // TODO log4j
        }
    }

    @Override
    public void update(long id, Client item) {

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

            while (resultSet.next()){
                client = new Client(
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }

            return client;
        } catch (SQLException e) {
            e.printStackTrace(); // TODO log4j
            return null;
        }
    }
}
