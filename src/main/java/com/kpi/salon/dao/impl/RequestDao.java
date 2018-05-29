package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.IRequestDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Client;
import com.kpi.salon.model.Master;
import com.kpi.salon.model.Request;
import com.kpi.salon.model.Status;
import com.kpi.salon.utils.ResourcesManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.kpi.salon.model.Status.ACTIVE;
import static com.kpi.salon.model.Status.CANCELED;
import static com.kpi.salon.model.Status.DONE;

public class RequestDao implements IRequestDao {
    private static final Logger LOGGER = Logger.getLogger(RequestDao.class);

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private Properties queries;

    public RequestDao() {
        init();
    }

    @Override
    public List<Request> findAll() {
        List<Request> requests = new ArrayList<>();
        String query = queries.getProperty("sql.request.find.all");

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(query);
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
                        resultSet.getString("description"),
                        resultSet.getString("avatar")
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

        } catch (NullPointerException | SQLException e) {
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
        return findAll().stream()
                .filter(request -> request.getId().longValue() == id)
                .findFirst()
                .get();
    }

    @Override
    public boolean insert(Request item) {
        String query = queries.getProperty("sql.request.insert");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setTimestamp(1, Timestamp.valueOf(item.getDate()));
            preparedStatement.setLong(2, item.getClient().getId());
            preparedStatement.setLong(3, item.getMaster().getId());

            int status_id = (item.getStatus() == ACTIVE)? 1 : ((item.getStatus() == DONE)? 2 : 3);
            preparedStatement.setLong(4, status_id);

            preparedStatement.executeUpdate();
            return true;

        } catch (NullPointerException | SQLException e) {
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
        return findByUser(masterId, queries.getProperty("sql.request.find.by.master"));
    }

    @Override
    public List<Request> findByMaster(Master master) {
        return findByMaster(master.getId());
    }

    @Override
    public List<Request> findByClient(Long clientId) {
        return findByUser(clientId, queries.getProperty("sql.request.find.by.client"));
    }

    @Override
    public List<Request> findByClient(Client client) {
        return findByClient(client.getId());
    }

    @Override
    public boolean updateStatus(Request request, Status status) {
        String query = queries.getProperty("sql.request.update");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            int status_id = (status == ACTIVE)? 1 : ((status == DONE)? 2 : 3);
            preparedStatement.setInt(1, status_id);
            preparedStatement.setLong(2, request.getId() );
            preparedStatement.executeUpdate();

            LOGGER.info("Status has been changed");
            LOGGER.info(request.getId());
            return true;

        } catch (NullPointerException | SQLException e) {
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
                        resultSet.getString("description"),
                        resultSet.getString("avatar")
                );

                requests.add(new Request(
                        resultSet.getLong("id"),
                        resultSet.getTimestamp("time").toLocalDateTime(),
                        client,
                        master,
                        status
                ));
            }

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return requests;
    }

    private void init() {
        try {
            queries = new ResourcesManager().getProperties("queries");
        } catch (IOException e) {
            LOGGER.error(String.format("Unable to download property file. %s", e.getMessage()), e);
        }
    }
}
