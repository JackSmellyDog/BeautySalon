package com.epam.salon.dao.request;

import com.epam.salon.datasource.ConnectionManager;
import com.epam.salon.model.Request;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class RequestDao implements IRequestDao {
    private static final Logger LOGGER = Logger.getLogger(RequestDao.class);

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String INSERT_REQUEST_QUERY = "INSERT INTO beauty_requests (appointment_time, client_id, master_id) VALUES (?, ?, ?)";

    @Override
    public List<Request> findAll() {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean findById(Long id) {
        return false;
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
            LOGGER.error(e.getMessage(), e);
        }
    }
}
