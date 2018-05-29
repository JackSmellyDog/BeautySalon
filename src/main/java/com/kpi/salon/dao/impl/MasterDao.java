package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.IMasterDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Master;
import com.kpi.salon.utils.ResourcesManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MasterDao implements IMasterDao {
    private static final Logger LOGGER = Logger.getLogger(MasterDao.class);

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private Properties queries;

    private static final int LOGIN_COLUMN = 1;
    private static final int PASSWORD_COLUMN = 2;
    private static final int NAME_COLUMN = 3;
    private static final int DESCRIPTION_COLUMN = 4;
    private static final int AVATAR_COLUMN = 5;

    public MasterDao() {
        init();
    }

    @Override
    public List<Master> findAll() {
        List<Master> masters = new ArrayList<>();
        String query = queries.getProperty("sql.master.find.all");

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                masters.add(new Master(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("avatar")
                ));
            }

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return masters;
    }

    @Override
    public boolean deleteById(Long id) {
        String query = queries.getProperty("sql.master.delete.by.id");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Master findById(Long id) {
        Master master = null;
        String query = queries.getProperty("sql.master.find.by.id");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                master = new Master(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("avatar")
                );
            }

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return master;
    }


    @Override
    public boolean insert(Master item) {
        String query = queries.getProperty("sql.master.insert");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){
            preparedStatement.setString(LOGIN_COLUMN, item.getLogin());
            preparedStatement.setString(PASSWORD_COLUMN, item.getPassword());
            preparedStatement.setString(NAME_COLUMN, item.getName());
            preparedStatement.setString(DESCRIPTION_COLUMN, item.getDescription());
            preparedStatement.setString(AVATAR_COLUMN, item.getAvatarFilename());
            preparedStatement.executeUpdate();
            return true;

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean update(Master item) {
        return false;
    }

    @Override
    public Master findByUsername(String username) {
        Master master = null;
        String query = queries.getProperty("sql.master.find.by.username");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                master = new Master(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("avatar")
                );
            }
        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return master;
    }

    private void init() {
        try {
            queries = new ResourcesManager().getProperties("queries");
        } catch (IOException e) {
            LOGGER.error(String.format("Unable to download property file. %s", e.getMessage()), e);
        }
    }
}
