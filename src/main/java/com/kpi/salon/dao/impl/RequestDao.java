package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.IRequestDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Client;
import com.kpi.salon.model.Master;
import com.kpi.salon.model.Request;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestDao implements IRequestDao {
    private static final Logger LOGGER = Logger.getLogger(RequestDao.class);

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    //private static final String ALL_REQUESTS_QUERY = "SELECT * FROM beauty_requests";
    private static final String REQUESTS_BY_MASTER_QUERY = "SELECT * FROM beauty_requests WHERE master_id=?";
    private static final String INSERT_REQUEST_QUERY = "INSERT INTO beauty_requests (appointment_time, client_id, master_id) VALUES (?, ?, ?)";
    private static final String ALL_REQUESTS_QUERY = "SELECT br.id, br.appointment_time, bc.id AS c_id, bc.login AS c_login, bc.password AS c_password, bm.id AS m_id, bm.login AS m_login, bm.password AS m_password, bm.name AS m_name, bm.description\n" +
            "FROM beauty_requests br\n" +
            "INNER JOIN beauty_clients bc ON br.client_id = bc.id\n" +
            "INNER JOIN beauty_masters bm ON br.master_id = bm.id";

    @Override
    public List<Request> findAll() {
        List<Request> requests = new ArrayList<>();

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(ALL_REQUESTS_QUERY);
            while (resultSet.next()){
                Client client = new Client(
                        resultSet.getLong("c_id"),
                        resultSet.getString("c_login"),
                        resultSet.getString("c_password")
                );

                Master master = new Master(
                        resultSet.getLong("m_id"),
                        resultSet.getString("m_login"),
                        resultSet.getString("m_password"),
                        resultSet.getString("m_name"),
                        resultSet.getString("description")
                );

                requests.add(new Request(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("appointment_time").toLocalDateTime(),
                        client,
                        master
                ));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return requests;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public Request findById(Long id) {
        return null;
    }

    @Override
    public boolean insert(Request item) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST_QUERY)
        ){
            preparedStatement.setTimestamp(1, Timestamp.valueOf(item.getDate()));
            preparedStatement.setLong(2, item.getClient().getId());
            preparedStatement.setLong(3, item.getMaster().getId());
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Request> findByMaster(Long masterId) {
        List<Request> requests = new ArrayList<>();

//        try(Connection connection = connectionManager.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(REQUESTS_BY_MASTER_QUERY)
//        ){
//            preparedStatement.setLong(1, masterId);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                requests.add(new Request(
//                        resultSet.getLong("id"),
//                        resultSet.getTimestamp("appointment_time").toLocalDateTime(),
//                        resultSet.getLong("client_id"),
//                        resultSet.getLong("master_id")
//                ));
//            }
//
//        } catch (SQLException e) {
//            LOGGER.error(e.getMessage(), e);
//        }

        return requests;
    }

    @Override
    public List<Request> findByMaster(Master master) {
        return findByMaster(master.getId());
    }
}
