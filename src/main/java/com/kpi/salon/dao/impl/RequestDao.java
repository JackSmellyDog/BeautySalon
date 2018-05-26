package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.IRequestDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Client;
import com.kpi.salon.model.Master;
import com.kpi.salon.model.Request;
import com.kpi.salon.model.Status;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.kpi.salon.model.Status.ACTIVE;
import static com.kpi.salon.model.Status.CANCELED;
import static com.kpi.salon.model.Status.DONE;

public class RequestDao implements IRequestDao {
    private static final Logger LOGGER = Logger.getLogger(RequestDao.class);

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    //private static final String ALL_REQUESTS_QUERY = "SELECT * FROM beauty_requests";
    //private static final String REQUESTS_BY_MASTER_QUERY = "SELECT * FROM beauty_requests WHERE master_id=?";
    private static final String INSERT_REQUEST_QUERY = "INSERT INTO beauty_requests (appointment_time, client_id, master_id, status_id) VALUES (?, ?, ?, ?)";
    private static final String ALL_REQUESTS_QUERY = "SELECT br.id AS id, br.appointment_time AS time, bc.id AS c_id, bc.login AS c_login, bc.password AS c_password, bm.id AS m_id, bm.login AS m_login, bm.password AS m_password, bm.name AS m_name, bm.description AS description, status_id\n" +
            "FROM beauty_requests br\n" +
            "INNER JOIN beauty_clients bc ON br.client_id = bc.id\n" +
            "INNER JOIN beauty_masters bm ON br.master_id = bm.id;";

    private static final String REQUESTS_BY_MASTER_QUERY = "SELECT br.id AS id, br.appointment_time AS time, bc.id AS c_id, bc.login AS c_login, bc.password AS c_password, bm.id AS m_id, bm.login AS m_login, bm.password AS m_password, bm.name AS m_name, bm.description AS description, status_id\n" +
            "FROM beauty_requests br\n" +
            "INNER JOIN beauty_clients bc ON br.client_id = bc.id\n" +
            "INNER JOIN beauty_masters bm ON br.master_id = bm.id\n" +
            "WHERE bm.id = ?;";

    private static final String REQUESTS_BY_CLIENT_QUERY = "SELECT br.id AS id, br.appointment_time AS time, bc.id AS c_id, bc.login AS c_login, bc.password AS c_password, bm.id AS m_id, bm.login AS m_login, bm.password AS m_password, bm.name AS m_name, bm.description AS description, status_id\n" +
            "FROM beauty_requests br\n" +
            "INNER JOIN beauty_clients bc ON br.client_id = bc.id\n" +
            "INNER JOIN beauty_masters bm ON br.master_id = bm.id\n" +
            "WHERE bc.id = ?;";


    private static final String UPDATE_REQUEST_STATUS = "UPDATE beauty_requests SET status_id=? WHERE id=?";

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

                Long status_id = resultSet.getLong("status_id");
                Status status = (status_id == 1)? ACTIVE : ((status_id == 2)? DONE : CANCELED);

                requests.add(new Request(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("time").toLocalDateTime(),
                        client,
                        master,
                        status
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
        // TODO maybe change it
        return findAll().stream()
                .filter(request -> request.getId().longValue() == id)
                .findFirst()
                .get();
    }

    @Override
    public boolean insert(Request item) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST_QUERY)
        ){
            preparedStatement.setTimestamp(1, Timestamp.valueOf(item.getDate()));
            preparedStatement.setLong(2, item.getClient().getId());
            preparedStatement.setLong(3, item.getMaster().getId());

            int status_id = (item.getStatus() == ACTIVE)? 1 : ((item.getStatus() == DONE)? 2 : 3);
            preparedStatement.setLong(4, status_id);

            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean update(Request item) {
        return false;
    }

    @Override
    public List<Request> findByMaster(Long masterId) {
        return findByUser(masterId, REQUESTS_BY_MASTER_QUERY);
    }

    @Override
    public List<Request> findByMaster(Master master) {
        return findByMaster(master.getId());
    }

    @Override
    public List<Request> findByClient(Long clientId) {
        return findByUser(clientId, REQUESTS_BY_CLIENT_QUERY);
    }

    @Override
    public List<Request> findByClient(Client client) {
        return findByClient(client.getId());
    }

    @Override
    public boolean updateStatus(Request request, Status status) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST_STATUS)
        ){
            int status_id = (status == ACTIVE)? 1 : ((status == DONE)? 2 : 3);
            preparedStatement.setInt(1, status_id);
            preparedStatement.setLong(2, request.getId() );
            preparedStatement.executeUpdate();

            LOGGER.info("Status has been changed");
            LOGGER.info(request.getId());
            return true;

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }


    private List<Request> findByUser(Long userId, String query) {
        List<Request> requests = new ArrayList<>();

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){

                Long status_id = resultSet.getLong("status_id");
                Status status = (status_id == 1)? ACTIVE : ((status_id == 2)? DONE : CANCELED);

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
                        resultSet.getTimestamp("time").toLocalDateTime(),
                        client,
                        master,
                        status
                ));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return requests;
    }
}
