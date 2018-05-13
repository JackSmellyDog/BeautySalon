package com.epam.salon.dao.request;

import com.epam.salon.datasource.ConnectionManager;
import com.epam.salon.model.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class RequestDao implements IRequestDao {
    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String INSERT_REQUEST_QUERY = "INSERT INTO beauty_requests (appointment_time, client_id, master_id) VALUES (?, ?, ?)";

    @Override
    public List<Request> findAll() {
        return null;
    }

    @Override
    public void insert(Request item) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST_QUERY)
        ){
            preparedStatement.setTimestamp(1, Timestamp.valueOf(item.getDate()));
            preparedStatement.setLong(2, item.getClientId());
            preparedStatement.setLong(3, item.getMasterId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(); // TODO log4j
        }
    }
}
