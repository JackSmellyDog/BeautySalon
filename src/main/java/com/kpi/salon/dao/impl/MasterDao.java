package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.IMasterDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Master;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterDao implements IMasterDao {
    private static final Logger LOGGER = Logger.getLogger(MasterDao.class);

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private static final String ALL_MASTERS_QUERY = "SELECT * FROM beauty_masters";
    private static final String BY_USERNAME_QUERY = "SELECT * FROM beauty_masters WHERE login='%s'";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM beauty_masters WHERE id=?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM beauty_masters WHERE id=?";
    private static final String INSERT_MASTER_QUERY = "INSERT INTO beauty_masters (login, password, name, description) VALUES (?, ?, ?, ?)";

    private static final int LOGIN_COLUMN = 1;
    private static final int PASSWORD_COLUMN = 2;
    private static final int NAME_COLUMN = 3;
    private static final int DESCRIPTION_COLUMN = 4;
    @Override
    public List<Master> findAll() {
        List<Master> masters = new ArrayList<>();

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(ALL_MASTERS_QUERY);
            while (resultSet.next()){
                masters.add(new Master(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                ));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return masters;
    }

    @Override
    public boolean deleteById(Long id) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)
        ){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        // TODO
        return false;
    }

    @Override
    public boolean findById(Long id) {
        return false;
    }


    @Override
    public void insert(Master item) {
        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MASTER_QUERY)
        ){
            preparedStatement.setString(LOGIN_COLUMN, item.getLogin());
            preparedStatement.setString(PASSWORD_COLUMN, item.getPassword());
            preparedStatement.setString(NAME_COLUMN, item.getName());
            preparedStatement.setString(DESCRIPTION_COLUMN, item.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public Master findByUsername(String username) {
        Master master = null;
        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){

            ResultSet resultSet = statement.executeQuery(String.format(
                    BY_USERNAME_QUERY,
                    username
            ));

            if (resultSet.next()){
                master = new Master(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return master;
    }
}
