package com.kpi.salon.dao.impl;

import com.kpi.salon.dao.IAdminDao;
import com.kpi.salon.datasource.ConnectionManager;
import com.kpi.salon.model.Admin;
import com.kpi.salon.model.Master;
import com.kpi.salon.utils.ResourcesManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AdminDao implements IAdminDao {
    private static final Logger LOGGER = Logger.getLogger(Admin.class);

    private ConnectionManager connectionManager = ConnectionManager.getInstance();
    private Properties queries;

    public AdminDao() {
        init();
    }

    @Override
    public List<Admin> findAll() {
        List<Admin> admins = new ArrayList<>();
        String query = queries.getProperty("sql.admin.find.all");

        try(Connection connection = connectionManager.getConnection();
            Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                admins.add(new Admin(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("phone")
                ));
            }

        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return admins;
    }

    @Override
    public boolean deleteById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Admin findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean insert(Admin item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(Admin item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Admin findByUsername(String username) {
        Admin admin = null;
        String query = queries.getProperty("sql.admin.find.by.username");

        try(Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)
        ){

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                admin = new Admin(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("phone")
                );
            }
        } catch (NullPointerException | SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return admin;
    }

    private void init() {
        try {
            queries = new ResourcesManager().getProperties("queries");
        } catch (IOException e) {
            LOGGER.error(String.format("Unable to download property file. %s", e.getMessage()), e);
        }
    }
}
