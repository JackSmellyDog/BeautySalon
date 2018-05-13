package com.epam.salon.dao.request;

import com.epam.salon.datasource.ConnectionManager;
import com.epam.salon.model.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RequestDao implements IRequestDao {
    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String INSERT_REQUEST_QUERY = "INSERT INTO beauty_clients (time, client_id, master_id) VALUES (?, ?, ?)";

    @Override
    public List<Request> findAll() {
        return null;
    }

    @Override
    public void insert(Request item) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST_QUERY)
        ){
            preparedStatement.setString(1, item.getDate());
            preparedStatement.setLong(2, item.getClientId());
            preparedStatement.setLong(3, item.getMasterId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // TODO log4j
        }
    }
}
